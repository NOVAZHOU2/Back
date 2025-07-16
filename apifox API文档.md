---
title: 个人项目
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.30"

---

# 个人项目

Base URLs:

# Authentication

# Summer/用户信息

## POST 录入用户数据

POST /api/persons

> Body 请求参数

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

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|name|query|string| 否 |none|
|body|body|object| 否 |none|

> 返回示例

> 201 Response

```json
{
  "personId": 0,
  "name": "string",
  "gender": 0,
  "idCard": "string",
  "phone": "string",
  "position": "string",
  "status": 0,
  "registerTime": [
    0
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|none|Inline|

### 返回数据结构

状态码 **201**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» personId|integer|true|none||none|
|» name|string|true|none||none|
|» gender|integer|true|none||none|
|» idCard|string|true|none||none|
|» phone|string|true|none||none|
|» position|string|true|none||none|
|» status|integer|true|none||none|
|» registerTime|[integer]|true|none||none|

## DELETE 删除用户数据

DELETE /api/persons/by-name

> Body 请求参数

```json
{
  "name": "张三"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "operation": "string",
  "targetName": "string",
  "statusCode": 0,
  "message": "string",
  "timestamp": [
    0
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» operation|string|true|none||none|
|» targetName|string|true|none||none|
|» statusCode|integer|true|none||none|
|» message|string|true|none||none|
|» timestamp|[integer]|true|none||none|

## PATCH 修改用户数据

PATCH /api/persons/update

> Body 请求参数

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

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "personId": null,
  "name": "string",
  "gender": 0,
  "idCard": "string",
  "phone": "string",
  "position": "string",
  "status": 0,
  "registerTime": null,
  "faceData": {
    "featureData": "string",
    "imagePath": "string",
    "qualityScore": 0,
    "version": "string"
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» personId|null|true|none||none|
|» name|string|true|none||none|
|» gender|integer|true|none||none|
|» idCard|string|true|none||none|
|» phone|string|true|none||none|
|» position|string|true|none||none|
|» status|integer|true|none||none|
|» registerTime|null|true|none||none|
|» faceData|object|true|none||none|
|»» featureData|string|true|none||none|
|»» imagePath|string|true|none||none|
|»» qualityScore|number|true|none||none|
|»» version|string|true|none||none|

## POST 查询用户

POST /api/persons/query

> Body 请求参数

```json
{
  "name": "李四"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "personId": 0,
  "name": "string",
  "gender": 0,
  "idCard": "string",
  "phone": "string",
  "position": "string",
  "status": 0,
  "registerTime": "string",
  "faceData": {
    "featureData": "string",
    "imagePath": "string",
    "qualityScore": 0,
    "version": "string"
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» personId|integer|true|none||none|
|» name|string|true|none||none|
|» gender|integer|true|none||none|
|» idCard|string|true|none||none|
|» phone|string|true|none||none|
|» position|string|true|none||none|
|» status|integer|true|none||none|
|» registerTime|string|true|none||none|
|» faceData|object|true|none||none|
|»» featureData|string|true|none||none|
|»» imagePath|string|true|none||none|
|»» qualityScore|number|true|none||none|
|»» version|string|true|none||none|

## GET 查找所有用户

GET /api/persons/list

> Body 请求参数

```json
{}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "content": [
    {
      "personId": 0,
      "name": "string",
      "gender": 0,
      "idCard": "string",
      "phone": "string",
      "position": "string",
      "status": 0,
      "registerTime": "string",
      "faceData": {
        "featureData": "string",
        "imagePath": "string",
        "qualityScore": 0,
        "version": "string"
      }
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 0,
    "sort": {
      "empty": true,
      "sorted": true,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": true
  },
  "last": true,
  "totalElements": 0,
  "totalPages": 0,
  "first": true,
  "numberOfElements": 0,
  "size": 0,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": true,
    "unsorted": true
  },
  "empty": true
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» content|[object]|true|none||none|
|»» personId|integer|true|none||none|
|»» name|string|true|none||none|
|»» gender|integer|true|none||none|
|»» idCard|string|true|none||none|
|»» phone|string|true|none||none|
|»» position|string|true|none||none|
|»» status|integer|true|none||none|
|»» registerTime|string|true|none||none|
|»» faceData|object|true|none||none|
|»»» featureData|string|true|none||none|
|»»» imagePath|string|true|none||none|
|»»» qualityScore|number|true|none||none|
|»»» version|string|true|none||none|
|» pageable|object|true|none||none|
|»» pageNumber|integer|true|none||none|
|»» pageSize|integer|true|none||none|
|»» sort|object|true|none||none|
|»»» empty|boolean|true|none||none|
|»»» sorted|boolean|true|none||none|
|»»» unsorted|boolean|true|none||none|
|»» offset|integer|true|none||none|
|»» paged|boolean|true|none||none|
|»» unpaged|boolean|true|none||none|
|» last|boolean|true|none||none|
|» totalElements|integer|true|none||none|
|» totalPages|integer|true|none||none|
|» first|boolean|true|none||none|
|» numberOfElements|integer|true|none||none|
|» size|integer|true|none||none|
|» number|integer|true|none||none|
|» sort|object|true|none||none|
|»» empty|boolean|true|none||none|
|»» sorted|boolean|true|none||none|
|»» unsorted|boolean|true|none||none|
|» empty|boolean|true|none||none|

# Summer/设备信息

## POST 新建设备

POST /api/devices

> Body 请求参数

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

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "deviceId": "string",
  "deviceName": "string",
  "deviceType": 0,
  "location": "string",
  "status": 0,
  "createTime": "string",
  "updateTime": "string",
  "remark": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» deviceId|string|true|none||none|
|» deviceName|string|true|none||none|
|» deviceType|integer|true|none||none|
|» location|string|true|none||none|
|» status|integer|true|none||none|
|» createTime|string|true|none||none|
|» updateTime|string|true|none||none|
|» remark|string|true|none||none|

## DELETE 删除设备

DELETE /api/devices

> Body 请求参数

```json
{
  "deviceId": "DEV-001"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "deviceId": "string",
  "code": 0,
  "timestamp": "string",
  "message": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» deviceId|string|true|none||none|
|» code|integer|true|none||none|
|» timestamp|string|true|none||none|
|» message|string|true|none||none|

## GET 查找所有设备

GET /api/devices

> Body 请求参数

```json
{}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
[
  {
    "deviceId": "string",
    "deviceName": "string",
    "deviceType": 0,
    "location": "string",
    "status": 0,
    "createTime": "string",
    "updateTime": "string",
    "remark": "string"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» deviceId|string|true|none||none|
|» deviceName|string|true|none||none|
|» deviceType|integer|true|none||none|
|» location|string|true|none||none|
|» status|integer|true|none||none|
|» createTime|string|true|none||none|
|» updateTime|string|true|none||none|
|» remark|string|true|none||none|

## PUT 更新设备

PUT /api/devices

> Body 请求参数

```json
{
  "deviceId": "DEV-002",
  "deviceName": "入口闸机",
  "deviceType": 1,
  "location": "科技园南门主通道",
  "status": 1,
  "createTime": "2025-07-16T09:55:56.9071706",
  "updateTime": "2025-07-16T09:55:56.9071706",
  "remark": "2025年新安装设备"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

## GET 查找设备（by-id）

GET /api/devices/by-id

> Body 请求参数

```json
{
  "deviceId": "DEV-001"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "deviceId": "string",
  "deviceName": "string",
  "deviceType": 0,
  "location": "string",
  "status": 0,
  "createTime": "string",
  "updateTime": "string",
  "remark": "string"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» deviceId|string|true|none||none|
|» deviceName|string|true|none||none|
|» deviceType|integer|true|none||none|
|» location|string|true|none||none|
|» status|integer|true|none||none|
|» createTime|string|true|none||none|
|» updateTime|string|true|none||none|
|» remark|string|true|none||none|

# Summer/日志信息

## POST 新建日志

POST /api/logs

> Body 请求参数

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

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "logId": 0,
  "personId": 0,
  "accessTime": "string",
  "accessType": 0,
  "result": 0,
  "temperature": 0,
  "confidence": 0,
  "captureImage": "string",
  "deviceId": "string",
  "person": null,
  "device": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» logId|integer|true|none||none|
|» personId|integer|true|none||none|
|» accessTime|string|true|none||none|
|» accessType|integer|true|none||none|
|» result|integer|true|none||none|
|» temperature|number|true|none||none|
|» confidence|number|true|none||none|
|» captureImage|string|true|none||none|
|» deviceId|string|true|none||none|
|» person|null|true|none||none|
|» device|null|true|none||none|

## GET 获取所有日志

GET /api/logs

> 返回示例

> 200 Response

```json
[
  {
    "logId": 0,
    "personId": 0,
    "accessTime": "string",
    "accessType": 0,
    "result": 0,
    "temperature": 0,
    "confidence": 0,
    "captureImage": "string",
    "deviceId": "string"
  }
]
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» logId|integer|false|none||none|
|» personId|integer|false|none||none|
|» accessTime|string|false|none||none|
|» accessType|integer|false|none||none|
|» result|integer|false|none||none|
|» temperature|number|false|none||none|
|» confidence|number|false|none||none|
|» captureImage|string|false|none||none|
|» deviceId|string|false|none||none|

## POST 删除日志

POST /api/logs/delete

> Body 请求参数

```json
{
  "logId": 3
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|Inline|

### 
