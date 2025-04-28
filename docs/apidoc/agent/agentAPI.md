---
title: 小助手接口
lang: zh-CN
outline: deep
---


[[toc]]

##### 简要描述

- 智能体chat接口

##### 请求URL
- https://openapi.tuniu.cn/agent/api/chat
  
##### 请求方式
- POST
- application/json
- 流式输出接口


##### 请求头

|  参数名 |参数描述   |类型   | 是否必填  |
| ------------ | ------------ | ------------ | ------------ |
|apikey   |开放平台访问秘钥   |String   |是   |



##### 请求体
|  参数名 |参数描述   |类型   | 是否必填  |
| ------------ | ------------ | ------------ | ------------ |
|sessionId   |会话id信息（如果不填会自动生成新的，在响应里带回去）   |String   |否  |
|requestId|标记单次请求的ID（如果不填会自动生成新的，在响应里带回去）|String|否|
|agentId|Agent的ID 机票：FlightTicketAgent 酒店：HotelAgent火车票：TrainTicketAgent|String   |是   |
|prompt|发送的消息|String|是|
|messages|用户自定义历史记忆（若同时传入sessionId和messages，则大模型优先使用messages中的内容，sessionId将被忽略，重新生成一个新的sessionId返回）|Message|否|

##### 请求示例

```json
{
    "sessionId": "xxx",
    "requestId": "xxx",
    "agentId": "TrainTicketAgent",
    "prompt": "明天从南京去厦门的火车票",
    "messages": [
        {
            "role": "user",
            "content": "你好"
        },
        {
            "role": "assistant",
            "content": "请问有什么需求"
        }
    ]
}
```

##### 响应体

|  参数名 |参数描述   |类型   | 是否必填  |
| ------------ | ------------ | ------------ | ------------ |
|statusCode   |响应码（200成功） |Integer   |    |
|message   |响应描述   |String   |   |
|sessionId   |会话id信息   |String   |  |
|requestId   |请求id信息   |String   |   |
|info   |具体的响应内容   |List\<Response\>   |   |
|apikey   |开放平台访问秘钥   |String   |   |
|usage|模型信息（只在流式输出最后一段内容里携带该信息）|Usage| |


- Response

|  参数名 |参数描述   |类型   | 是否必填  |
| ------------ | ------------ | ------------ | ------------ |
|type   |文本类型 |String   |    |
|content   |具体响应内容 |String   |    ||

- Usage

|  参数名 |参数描述   |类型   | 是否必填  |
| ------------ | ------------ | ------------ | ------------ |
|model   |模型信息 |String   |    |
|inputTokens   |输入token数量 |Integer   |    |
|outputTokens   |输出token数量 |Integer   |   ||

```json
{
    "statusCode": 200,
    "message": null,
    "sessionId": "20250343222",
    "requestId": "34983848394",
    "info": [
        {
            "type": "analysis",
            "content": "- 正在调用 `火车票小助手` 为您服务\n"
        }
    ],
    "usage": {
      "model":"qwen2.5-32b-instruct",
      "inputTokens":1800,
      "outputTokens":200
    }
}
```
```

