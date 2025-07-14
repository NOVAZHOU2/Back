# 门禁管理系统 API 文档

## 项目概述
门禁管理系统使用 AI 人脸识别技术，进行人员以及出入数据统计。



## 数据库设计

### 1. **person表（人员基础表）**

| 字段名 |    类型     | 是否为空 | 默认值 |    约束     |       说明       |
| :----: | :---------: | :------: | :----: | :---------: | :--------------: |
|   id   |   BIGINT    | NOT NULL |   -    | PRIMARY KEY | 雪花算法生成的ID |
|  name  | VARCHAR(50) | NOT NULL |   -    |      -      |       姓名       |

------

### 2. **person_detail表（人员详细信息表）**

|    字段名     |    类型     | 是否为空 |           默认值            |    约束     |         说明          |
| :-----------: | :---------: | :------: | :-------------------------: | :---------: | :-------------------: |
|   person_id   |   BIGINT    | NOT NULL |              -              | FOREIGN KEY |   关联person表的id    |
|     name      | VARCHAR(50) | NOT NULL |              -              |      -      |   姓名（冗余存储）    |
|    gender     |   TINYINT   |   YES    |            NULL             |      -      | 性别:0-女,1-男,2-其他 |
|    id_card    | VARCHAR(18) |   YES    |            NULL             |   UNIQUE    |   身份证号（唯一）    |
|     phone     | VARCHAR(20) |   YES    |            NULL             |      -      |        手机号         |
|   position    | VARCHAR(50) |   YES    |            NULL             |      -      |         身份          |
| register_time |  DATETIME   |   YES    |      CURRENT_TIMESTAMP      |      -      |       注册时间        |
|  update_time  |  DATETIME   |   YES    | CURRENT_TIMESTAMP ON UPDATE |      -      |       更新时间        |
|    status     |   TINYINT   |   YES    |              1              |      -      | 状态:0-禁止,1-可通行  |

**索引**：`idx_name`（姓名索引）

------

### 3. **face_data表（人脸特征表）**

|    字段名     |     类型     | 是否为空 |      默认值       |    约束     |       说明        |
| :-----------: | :----------: | :------: | :---------------: | :---------: | :---------------: |
|   person_id   |    BIGINT    | NOT NULL |         -         | FOREIGN KEY | 关联person表的id  |
| feature_data  |     BLOB     | NOT NULL |         -         |      -      |   人脸特征数据    |
|  image_path   | VARCHAR(255) |   YES    |       NULL        |      -      |   人脸图片路径    |
| register_time |   DATETIME   |   YES    | CURRENT_TIMESTAMP |      -      |     注册时间      |
| quality_score |    FLOAT     |   YES    |       NULL        |      -      | 人脸质量评分(0-1) |
|    version    | VARCHAR(20)  |   YES    |       NULL        |      -      | 特征提取算法版本  |

**索引**：`idx_person`（人员ID索引）

------

### 4. **access_device表（门禁设备表）**

|   字段名    |     类型     | 是否为空 |           默认值            |    约束     |           说明            |
| :---------: | :----------: | :------: | :-------------------------: | :---------: | :-----------------------: |
|  device_id  | VARCHAR(36)  | NOT NULL |              -              | PRIMARY KEY |       设备唯一标识        |
| device_name | VARCHAR(100) | NOT NULL |              -              |      -      |         设备名称          |
| device_type |   TINYINT    | NOT NULL |              -              |      -      | 类型:1-入口,2-出口,3-双向 |
|  location   | VARCHAR(200) | NOT NULL |              -              |      -      |         安装位置          |
|   status    |   TINYINT    | NOT NULL |              1              |      -      | 状态:0-离线,1-在线,2-故障 |
| create_time |   DATETIME   | NOT NULL |      CURRENT_TIMESTAMP      |      -      |         创建时间          |
| update_time |   DATETIME   | NOT NULL | CURRENT_TIMESTAMP ON UPDATE |      -      |       最后更新时间        |
|   remark    | VARCHAR(200) |   YES    |            NULL             |      -      |           备注            |

------

### 5. **access_log表（通行记录表）**

|    字段名     |     类型     | 是否为空 |      默认值       |    约束     |             说明              |
| :-----------: | :----------: | :------: | :---------------: | :---------: | :---------------------------: |
|   person_id   |    BIGINT    |   YES    |       NULL        | FOREIGN KEY |        识别到的人员ID         |
|  access_time  |   DATETIME   |   YES    | CURRENT_TIMESTAMP |      -      |           通行时间            |
|  access_type  |   TINYINT    |   YES    |       NULL        |      -      | 识别方式:1-人脸,2-刷卡,3-混合 |
|    result     |   TINYINT    |   YES    |       NULL        |      -      |      结果:0-拒绝,1-通过       |
|  temperature  |    FLOAT     |   YES    |       NULL        |      -      |           检测体温            |
|  confidence   |    FLOAT     |   YES    |       NULL        |      -      |          识别置信度           |
| capture_image | VARCHAR(255) |   YES    |       NULL        |      -      |          抓拍图路径           |
|   device_id   | VARCHAR(36)  |   YES    |       NULL        | FOREIGN KEY |          设备序列号           |

**索引**：

- `idx_time`（时间查询优化）
- `idx_person`（人员查询优化）



## API 接口文档

### 基础信息
- **基础URL**: `/api`

- **默认响应格式**: JSON

- **认证方式**: 
  ```http
  Authorization: Bearer <token>
  ```

### 接口目录

### 1. 创建用户

- **请求方法**: POST

- **路径**: `/persons`

- **请求体**:

  ```json
  {
    "name": "张三",
    "gender": 1,
    "idCard": "110101199003072345",
    "phone": "13800138000",
    "position": "学生",
    "status": 0,
    "faceData": {
      "featureData": "aGVsbG8gd29ybGQ=",
      "imagePath": "/upload/faces/2025/zhangsan.jpg",
      "qualityScore": 95.5,
      "version": "v1.2.0"
    }
  }
  ```

- **响应体（201 Created）**:

  ```
  {
      "personId": 10,
      "name": "兔吮",
      "gender": 1,
      "idCard": "110101109003271245",
      "phone": "13800138000",
      "position": "教师",
      "status": 0,
      "registerTime": "2025-07-14T15:25:13.02071",
      "faceData": null
  }
  ```

------

### 2. 删除用户

- **请求方法**: DELETE

- **路径**: `/persons/by-name`

- **请求体**:

  ```json
  {
    "name": "张三"
  }
  ```

- **响应体（201 Created）**:

  ```
  {
      "operation": "DELETE",
      "targetName": "jesper",
      "statusCode": 200,
      "message": "成功删除姓名为 [jesper] 的记录",
      "timestamp": "2025-07-14T11:44:02.665719"
  }
  ```

------

### 3. 修改用户

- **请求方法**: PATCH

- **路径**: `/persons/update`

- **请求体**:

  ```json
  {
    "name": "李四",              
    "gender": 1,                
    "idCard": "110101199001011234", 
    "phone": "13800138000",    
    "position": "工程师",       
    "status": 1,                 
    "faceData": {                
      "featureData": "hell", 
      "imagePath": "/path/to/image.jpg", 
      "qualityScore": 95.5,     
      "version": "v1.0"       
    }
  }
  ```

- **响应体（201 Created）**:

  ```
  {
      "personId": 6,
      "name": "李四",
      "gender": 1,
      "idCard": "110101199001011234",
      "phone": "13800138000",
      "position": "工程师",
      "status": 1,
      "registerTime": "2025-07-14T09:32:26",
      "faceData": {
          "featureData": "hell",
          "imagePath": "/path/to/image.jpg",
          "qualityScore": 95.5,
          "version": "v1.0"
      }
  }
  ```

------

### 4. 查询用户（by-name)

- **请求方法**: POST

- **路径**: `/persons/query`

- **请求体**:

  ```json
  {
      "name" : "李四"
  }
  ```

- **响应体（201 Created）**:

  ```
  {
      "personId": 6,
      "name": "李四",
      "gender": 1,
      "idCard": "110101199001011234",
      "phone": "13800138000",
      "position": "工程师",
      "status": 1,
      "registerTime": "2025-07-14T09:32:26",
      "faceData": {
          "featureData": "hell",
          "imagePath": "/path/to/image.jpg",
          "qualityScore": 95.5,
          "version": "v1.0"
      }
  }
  ```

------

