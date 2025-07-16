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

|    字段名     |     类型     | 是否为空 |      默认值       |            约束             |             说明              |
| :-----------: | :----------: | :------: | :---------------: | :-------------------------: | :---------------------------: |
|  **log_id**   |    BIGINT    |    NO    |         -         | PRIMARY KEY, AUTO_INCREMENT |       日志主键（新增）        |
|   person_id   |    BIGINT    |   YES    |       NULL        |         FOREIGN KEY         |        识别到的人员ID         |
|  access_time  |   DATETIME   |   YES    | CURRENT_TIMESTAMP |              -              |           通行时间            |
|  access_type  |   TINYINT    |   YES    |       NULL        |              -              | 识别方式:1-人脸,2-刷卡,3-混合 |
|    result     |   TINYINT    |   YES    |       NULL        |              -              |      结果:0-拒绝,1-通过       |
|  temperature  |    FLOAT     |   YES    |       NULL        |              -              |           检测体温            |
|  confidence   |    FLOAT     |   YES    |       NULL        |              -              |          识别置信度           |
| capture_image | VARCHAR(255) |   YES    |       NULL        |              -              |          抓拍图路径           |

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
    "name": "兔吮2",
    "gender": 1,
    "idCard": "210101109012271125",
    "phone": "13800138000",
    "position": "教师",
    "status": 0,
    "faceData": {
      "faceToken": "12345",
      "groupId": "1",
      "logId":1
    }
  }
  ```
  
- **响应体（201 Created）**:

  ```
  {
      "personId": 14,
      "name": "兔吮2",
      "gender": 1,
      "idCard": "210101109012271125",
      "phone": "13800138000",
      "position": "教师",
      "status": 0,
      "registerTime": "2025-07-14T17:15:24.5033405",
      "faceData": {
          "faceToken": "12345",
          "groupId": "1",
          "logId": 1
      }
  }
  ```

------

### 2. 删除用户

- **请求方法**: DELETE

- **路径**: `/persons/by-name`

- **请求体**:

  ```json
  {
    "name": "兔吮2"
  }
  ```

- **响应体（20）**:

  ```
  {
      "operation": "DELETE",
      "targetName": "兔吮2",
      "statusCode": 200,
      "message": "成功删除姓名为 [兔吮2] 的记录",
      "timestamp": "2025-07-14T17:17:02.1175023"
  }
  ```

------

### 3. 修改用户

- **请求方法**: PATCH

- **路径**: `/persons/update`

- **请求体**:

  ```json
  {
    "name": "兔吮1",
    "gender": 1,
    "idCard": "210101109012271125",
    "phone": "13800138000",
    "position": "教师",
    "status": 0,
    "faceData": {
      "faceToken": "54321",
      "groupId": "1",
      "logId":1
    }
  }
  ```

- **响应体（20）**:

  ```
  {
      "personId": 13,
      "name": "兔吮1",
      "gender": 1,
      "idCard": "210101109012271125",
      "phone": "13800138000",
      "position": "教师",
      "status": 0,
      "registerTime": "2025-07-14T17:02:15",
      "faceData": {
          "faceToken": "54321",
          "groupId": "1",
          "logId": 1
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
      "name" : "兔吮1"
  }
  ```

- **响应体（200）**:

  ```
  {
      "personId": 13,
      "name": "兔吮1",
      "gender": 1,
      "idCard": "210101109012271125",
      "phone": "13800138000",
      "position": "教师",
      "status": 0,
      "registerTime": "2025-07-14T17:02:15",
      "faceData": {
          "faceToken": "54321",
          "groupId": "1",
          "logId": 1
      }
  }
  ```

------

### 5. 查询用户（all)

- **请求方法**: GET

- **路径**: `/persons/list`

- **请求体**:

  ```json
  {
  
  }
  ```

- **响应体（200）**:

  ```
  {
      "content": [
          {
              "personId": 12,
              "name": "兔吮",
              "gender": 1,
              "idCard": "110101109012271245",
              "phone": "13800138000",
              "position": "教师",
              "status": 0,
              "registerTime": "2025-07-14T16:52:46",
              "faceData": {
                  "faceToken": "12345",
                  "groupId": "1",
                  "logId": 1
              }
          },
          {
              "personId": 13,
              "name": "兔吮1",
              "gender": 1,
              "idCard": "210101109012271125",
              "phone": "13800138000",
              "position": "教师",
              "status": 0,
              "registerTime": "2025-07-14T17:02:15",
              "faceData": {
                  "faceToken": "54321",
                  "groupId": "1",
                  "logId": 1
              }
          }
      ],
      "pageable": {
          "pageNumber": 0,
          "pageSize": 10,
          "sort": {
              "empty": false,
              "sorted": true,
              "unsorted": false
          },
          "offset": 0,
          "paged": true,
          "unpaged": false
      },
      "last": true,
      "totalElements": 2,
      "totalPages": 1,
      "size": 10,
      "number": 0,
      "sort": {
          "empty": false,
          "sorted": true,
          "unsorted": false
      },
      "first": true,
      "numberOfElements": 2,
      "empty": false
  }
  ```

------

### 6. 新建设备

- **请求方法**: POST

- **路径**: `/device`

- **请求体**:

  ```json
  {
    "deviceId": "DEV-001",
    "deviceName": "南门入口闸机",
    "deviceType": 1,
    "location": "科技园南门主通道",
    "status": 1,
    "remark": "2025年新安装设备"
  }
  ```

- **响应体（200）**:

  ```
  {
      "deviceId": "DEV-001",
      "deviceName": "南门入口闸机",
      "deviceType": 1,
      "location": "科技园南门主通道",
      "status": 1,
      "createTime": "2025-07-16T09:55:56.9071706",
      "updateTime": "2025-07-16T09:55:56.9071706",
      "remark": "2025年新安装设备"
  }
  ```

------

### 7. 删除设备

- **请求方法**: DELETE

- **路径**: `/devices`

- **请求体**:

  ```json
  {
       "deviceId" : "DEV-001" 
  }
  ```

- **响应体（200）**:

  ```
  {
      "deviceId": "DEV-001",
      "code": 200,
      "timestamp": "2025-07-16T10:06:02.8201593",
      "message": "设备删除成功"
  }
  ```

------

### 8. 查找设备（通过device_id）

- **请求方法**: GET

- **路径**: `/devices/by-id`

- **请求体**:

  ```json
  {
      "deviceId" : "DEV-001"
  }
  ```

- **响应体（200）**:

  ```
  {
      "deviceId": "DEV-001",
      "deviceName": "南门入口闸机",
      "deviceType": 1,
      "location": "科技园南门主通道",
      "status": 1,
      "createTime": "2025-07-16T10:12:18",
      "updateTime": "2025-07-16T10:12:18",
      "remark": "2025年新安装设备"
  }
  ```

------

### 9. 查找所有设备

- **请求方法**: GET

- **路径**: `/devices`

- **请求体**:

  ```json
  
  ```

- **响应体（200）**:

  ```
  [
      {
          "deviceId": "DEV-001",
          "deviceName": "南门入口闸机",
          "deviceType": 1,
          "location": "科技园南门主通道",
          "status": 1,
          "createTime": "2025-07-16T10:12:18",
          "updateTime": "2025-07-16T10:12:18",
          "remark": "2025年新安装设备"
      },
      {
          "deviceId": "DEV-002",
          "deviceName": "西门入口闸机",
          "deviceType": 1,
          "location": "科技园西门主通道",
          "status": 1,
          "createTime": "2025-07-16T10:14:21",
          "updateTime": "2025-07-16T10:14:21",
          "remark": "2024年安装设备"
      }
  ]
  ```

------

### 10. 更新设备信息

- **请求方法**: UPDATE

- **路径**: `/devices`

- **请求体**:

  ```json
  {
      "deviceId": "DEV-002",//主键不支持修改
      "deviceName": "入口闸机",
      "deviceType": 1,
      "location": "科技园南门主通道",
      "status": 1,
      "createTime": "2025-07-16T09:55:56.9071706",
      "updateTime": "2025-07-16T09:55:56.9071706",
      "remark": "2025年新安装设备"
  }
  ```

- **响应体（200）**:

  ```
  {
      "deviceId": "DEV-002",
      "deviceName": "入口闸机",
      "deviceType": 1,
      "location": "科技园南门主通道",
      "status": 1,
      "createTime": "2025-07-16T09:55:56.9071706",
      "updateTime": "2025-07-16T10:20:04.4166138",
      "remark": "2025年新安装设备"
  }
  ```

------

### 11. 创建日志（确保外键personId和deviceId存在）

- **请求方法**: POST

- **路径**: `/logs`

- **请求体**:

  ```json
  {
    "personId": 12,
    "accessType": 1,
    "result": 1,
    "temperature": 36.5,
    "confidence": 0.95,
    "captureImage": "/snapshots/2025/1001.jpg",
    "deviceId": "DEV-001"
  }
  ```

- **响应体（200）**:

  ```
  {
      "logId": 2,
      "personId": 12,
      "accessTime": "2025-07-16T10:54:44.58811",
      "accessType": 1,
      "result": 1,
      "temperature": 36.5,
      "confidence": 0.95,
      "captureImage": "/snapshots/2025/1001.jpg",
      "deviceId": "DEV-001",
      "person": null,
      "device": null
  }
  ```

------

### 12. 获取所有日志

- **请求方法**: GET

- **路径**: `/logs`

- **请求体**:

  ```json
  
  ```

- **响应体（200）**:

  ```
  [
      {
          "logId": 2,
          "personId": 12,
          "accessTime": "2025-07-16T10:54:45",
          "accessType": 1,
          "result": 1,
          "temperature": 36.5,
          "confidence": 0.95,
          "captureImage": "/snapshots/2025/1001.jpg",
          "deviceId": "DEV-001"
      }
  ]
  ```

------

### 13. 删除日志

- **请求方法**: POST

- **路径**: `/logs/delete`

- **请求体**:

  ```json
  {
      "logId" : 1
  }
  ```

- **响应体（200）**:

  ```
  //日志存在时：
  	删除成功
  //日志不存在时：
  	日志不存在
  ```

------

### 
