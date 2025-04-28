---
# https://vitepress.dev/reference/default-theme-home-page
layout: doc
title: 首次调用
---


# 首次调用


- 服务地址：https://openapi.tuniu.cn
- 申请服务密钥: <a href="https://platform.tuniu.com/apiKeys?title=API+Keys">前往</a>
- 访问示例

```bash
curl https://openapi.tuniu.cn/agent/api/chat \
  -H "Content-Type: application/json" \
  -H "apikey: <TUNIU OPENAPI Key>" \
  -d '{
    "sessionId": "123456789",
    "agentId": "HotelAgent",
    "prompt": "明天南京火车站附近的酒店",
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
}'
