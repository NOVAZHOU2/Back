# 门禁管理系统 API 文档

## 项目概述
门禁管理系统使用 AI 人脸识别技术，进行人员以及出入数据统计。



## 数据库设计

### 1. **person表（人员基础表）**

| 字段名 |    类型     | 是否为空 | 默认值 |    约束     |  说明  |
| :----: | :---------: | :------: | :----: | :---------: | :----: |
|   id   |   BIGINT    | NOT NULL |   -    | PRIMARY KEY | 人员ID |
|  name  | VARCHAR(50) | NOT NULL |   -    |      -      |  姓名  |

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

------

### 3. **face_data表（人脸特征表）**

|    字段名     |     类型     | 是否为空 |      默认值       | 约束        |            说明            |
| :-----------: | :----------: | :------: | :---------------: | ----------- | :------------------------: |
|   person_id   |    BIGINT    | NOT NULL |         -         | FOREIGN KEY |  关联人员ID，等效user_id   |
|  face_token   | VARCHAR(255) |   YES    |       NULL        | -           |   人脸图片唯一标识token    |
|   group_id    | VARCHAR(255) |   YES    |       NULL        | -           |          分组信息          |
|    log_id     | VARCHAR(255) |   YES    |       NULL        | -           | 请求标识码（随机数，唯一） |
|   face_url    | VARCHAR(255) |   YES    |       NULL        | -           |          图片路径          |
| register_time |   DATETIME   |   YES    | CURRENT_TIMESTAMP | -           |          注册时间          |

------

### 4. **access_device表（门禁设备表）**

|   字段名    |     类型      | 是否为空 |           默认值            |    约束     |           说明            |
| :---------: | :-----------: | :------: | :-------------------------: | :---------: | :-----------------------: |
|  device_id  |  VARCHAR(36)  | NOT NULL |              -              | PRIMARY KEY |       设备唯一标识        |
| device_name | VARCHAR(100)  | NOT NULL |              -              |      -      |         设备名称          |
| device_type |    TINYINT    | NOT NULL |              -              |      -      | 类型:1-入口,2-出口,3-双向 |
|  location   | VARCHAR(200)  | NOT NULL |              -              |      -      |         安装位置          |
|   status    |    TINYINT    | NOT NULL |              1              |      -      | 状态:0-离线,1-在线,2-故障 |
| create_time |   DATETIME    | NOT NULL |      CURRENT_TIMESTAMP      |      -      |         创建时间          |
| update_time |   DATETIME    | NOT NULL | CURRENT_TIMESTAMP ON UPDATE |      -      |       最后更新时间        |
|   remark    | VARCHAR(200)  |   YES    |            NULL             |      -      |           备注            |
|  longitude  | DECIMAL(10,6) |   YES    |            NULL             |      -      |           经度            |
|  latitude   | DECIMAL(10,6) |   YES    |            NULL             |      -      |           纬度            |

------

### 5. **access_log表（通行记录表）**

|    字段名     |     类型     | 是否为空 |      默认值       |            约束             |             说明              |
| :-----------: | :----------: | :------: | :---------------: | :-------------------------: | :---------------------------: |
|  **log_id**   |    BIGINT    |    NO    |         -         | PRIMARY KEY, AUTO_INCREMENT |       日志主键（新增）        |
|   device_id   | VARCHAR(36)  |    NO    |         -         |         FOREIGN KEY         |            设备ID             |
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
    }
  }
  ```
  
- **响应体（201 Created）**:

  ```json
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

  ```json
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
    }
  }
  ```
  
- **响应体（20）**:

  ```json
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

  ```json
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
          "logId": 1,
           "faceUrl": "E:\\Desktop"
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

  ```json
  {
      "content": [
          {
              "personId": 1,
              "name": "兔吮2",
              "gender": 1,
              "idCard": "210101109012271125",
              "phone": "13800138000",
              "position": "教师",
              "status": 0,
              "registerTime": "2025-07-19T16:37:49",
              "faceData": {
                  "faceToken": "bbdb3931aa2e5d1955d648128dcd7fe9",
                  "groupId": "2",
                  "logId": 3092390589,
                  "faceUrl": "E:\\Desktop"
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
      "totalPages": 1,
      "totalElements": 1,
      "size": 10,
      "number": 0,
      "sort": {
          "empty": false,
          "sorted": true,
          "unsorted": false
      },
      "first": true,
      "numberOfElements": 1,
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
    "deviceId": "DEV-002",
    "deviceName": "南门入口闸机",
    "deviceType": 1,
    "location": "科技园南门主通道",
    "status": 1,
    "remark": "2025年新安装设备",
    "longitude":1.1,
    "latitude":1.1
  }
  
  ```

- **响应体（200）**:

  ```json
  {
      "deviceId": "DEV-002",
      "deviceName": "南门入口闸机",
      "deviceType": 1,
      "location": "科技园南门主通道",
      "status": 1,
      "createTime": "2025-07-18T15:22:19.247795",
      "updateTime": "2025-07-18T15:22:19.247795",
      "remark": "2025年新安装设备",
      "longitude": 1.1,
      "latitude": 1.1
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

  ```json
  {
      "code": 200,
      "deviceId": "DEV-002",
      "message": "设备删除成功",
      "timestamp": "2025-07-18T15:24:35.2900179"
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

  ```json
  {
      "deviceId": "DEV-002",
      "deviceName": "入口闸机",
      "deviceType": 1,
      "location": "科技园南门主通道",
      "status": 1,
      "createTime": "2025-07-18T15:22:19",
      "updateTime": "2025-07-18T15:23:08",
      "remark": "2025年新安装设备",
      "longitude": 1.2,
      "latitude": 1.2
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

  ```json
  [
      {
          "deviceId": "DEV-002",
          "deviceName": "入口闸机",
          "deviceType": 1,
          "location": "科技园南门主通道",
          "status": 1,
          "createTime": "2025-07-18T15:22:19",
          "updateTime": "2025-07-18T15:23:08",
          "remark": "2025年新安装设备",
          "longitude": 1.2,
          "latitude": 1.2
      }
  ]
  ```

------

### 10. 更新设备信息

- **请求方法**: PUT

- **路径**: `/devices`

- **请求体**:

  ```json
  {
      "deviceId": "DEV-002",
      "deviceName": "入口闸机",
      "deviceType": 1,
      "location": "科技园南门主通道",
      "status": 1,
      "createTime": "2025-07-16T09:55:56.9071706",
      "updateTime": "2025-07-16T09:55:56.9071706",
      "remark": "2025年新安装设备",
      "longitude":1.2,
      "latitude":1.2
  }
  ```

- **响应体（200）**:

  ```json
  {
      "deviceId": "DEV-002",
      "deviceName": "入口闸机",
      "deviceType": 1,
      "location": "科技园南门主通道",
      "status": 1,
      "createTime": "2025-07-16T09:55:56.9071706",
      "updateTime": "2025-07-18T15:23:07.9462601",
      "remark": "2025年新安装设备",
      "longitude": 1.2,
      "latitude": 1.2
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

  ```json
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

  ```json
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

  ```json
  //日志存在时：
  	删除成功
  //日志不存在时：
  	日志不存在
  ```

------

### 14. 录入人脸数据

- **请求方法**: POST

- **路径**: `/recognize/register`

- **请求体**:

  ```json
  //Params:
  	personId:12
  	groupId:1
  	faceUrl:E:\Desktop\photo.jpg
  	userInfo:jesper
  //formdata:
  	file:photo.png
  ```

- **响应体（200）**:

  ```json
  {
      "result": {
          "face_token": "bbdb3931aa2e5d1955d648128dcd7fe9",
          "location": {
              "top": 1312.51,
              "left": 251.16,
              "rotation": 0,
              "width": 988,
              "height": 1019
          }
      },
      "log_id": 3744678848,
      "error_msg": "SUCCESS",
      "cached": 0,
      "error_code": 0,
      "timestamp": 1752721429
  }
  ```

------

### 15. 删除人脸数据

- **请求方法**: DELETE

- **路径**: `/recognize/delete`

- **请求体**:

  ```json
  //Params:
  	personId:24
  ```

- **响应体（200）**:

  ```json
  {
      "result": true,
      "log_id": 3167015145,
      "error_msg": "SUCCESS",
      "cached": 0,
      "error_code": 0,
      "timestamp": 1752721472
  }
  ```

------

### 16. 修改人脸数据

- **请求方法**: PUT

- **路径**: `/recognize/update`

- **请求体**:

  ```json
  //Params:
  	personId:24
  	groupId:2
  	userInfo:jesper2
  //formdata:
  	file:photo.png
  ```

- **响应体（200）**:

  ```json
  {
      "result": {
          "face_token": "bbdb3931aa2e5d1955d648128dcd7fe9",
          "location": {
              "top": 1312.56,
              "left": 251.17,
              "rotation": 0,
              "width": 988,
              "height": 1019
          }
      },
      "log_id": 1734166057,
      "error_msg": "SUCCESS",
      "cached": 0,
      "error_code": 0,
      "timestamp": 1752721736
  }
  ```

------

### 17. 人脸识别

- **请求方法**: GET

- **路径**: `/recognize/recognize`

- **请求体**:

  ```json
  //Params:
  	groupIdList: 1,2,3
  //formdata:
  	file:photo.png
  ```

- **响应体（200）**:

  ```json
  {
      "id": 12,
      "name": "兔吮"
  }
  ```

------

### 18. 用户信息批量删除

- **请求方法**: DELETE

- **路径**: `/persons/batch-by-name`

- **请求体**:

  ```json
  {
      "names":["兔吮7","兔吮8"]
  }
  ```

- **响应体（200）**:

  ```json
  {
      "operation": "BATCH_DELETE",
      "targetNames": [
          "兔吮7",
          "兔吮8"
      ],
      "statusCode": 200,
      "message": "成功删除 2/2 条记录",
      "timestamp": "2025-07-18T11:50:03.828195",
      "successCount": 0,
      "failureCount": 2
  }
  ```

------

### 19. 设备信息批量删除

- **请求方法**: DELETE

- **路径**: `/devices/batch`

- **请求体**:

  ```json
  {
      "deviceIds": ["DEV-002", "DEV-100","DEV-200"]
  }
  ```

- **响应体（200）**:

  ```json
  {
      "code": 200,
      "timestamp": "2025-07-18T16:03:21.2305083",
      "message": "成功删除 3 台设备",
      "data": {
          "deviceIds": [
              "DEV-002",
              "DEV-100",
              "DEV-200"
          ]
      }
  }
  ```

------

### 20. 日志信息批量删除

- **请求方法**: POST

- **路径**: `/logs/batch`

- **请求体**:

  ```json
  {
      "logIds": [13, 14, 15]
  }
  ```

- **响应体（200）**:

  ```json
  {
      "logIds": [
          13,
          14,
          15
      ],
      "code": 200,
      "timestamp": "2025-07-18T15:58:22.4109521",
      "message": "成功删除 3 条日志"
  }
  ```

------

### 21. 获取进入日志数据

- **请求方法**: GET

- **路径**: `logs/device/in`

- **请求体**:

  ```json
  
  ```

- **响应体（200）**:

  ```json
  [
      {
          "deviceId": "DEV-001",
          "count": 2
      }
  ]
  ```

------

### 22. 获取离开日志数据

- **请求方法**: GET

- **路径**: `logs/device/stop`

- **请求体**:

  ```json
  
  ```

- **响应体（200）**:

  ```json
  [
      {
          "deviceId": "DEV-001",
          "count": 2
      },
      {
          "deviceId": "DEV-002",
          "count": 2
      }
  ]
  ```

------

### 23. 获取出入人员数据

- **请求方法**: GET

- **路径**: `logs/device/stop`

- **请求体**:

  ```json
  
  ```

- **响应体（200）**:

  ```json
  [
      {
          "position": "工程师",
          "count": 2
      }
  ]
  ```
