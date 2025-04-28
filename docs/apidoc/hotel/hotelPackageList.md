---
title: 酒店套餐列表
lang: zh-CN
outline: deep
---


##### 简要描述

- 酒店套餐列表查询接口

##### 请求URL
- https://openapi.tuniu.cn/hts/agent/package/hotel/list
  
##### 请求方式
- POST 

##### 请求参数

| 字段名称 | 字段编码 | 字段类型 | 非空 | 说明 |
| --- | --- | --- | --- | --- |
| 城市code | cityCode | String | 是 | 与cityName必传1个 |
| 城市名称 | cityName | String | 是 | 与cityName必传1个|
| 行程天数 | journeyNum | String | 否| 默认1，代表2天1晚|
| 入住日期 | checkIn | String | 否 | 优先客户指定日期默认当天+1 |
| 离店日期 | checkOut | String | 否 | 默认入住日期+1 |
| poi名称 | poiName | String | 否 | 用户输入地点时传输，未输入时为空；用户输入地点时，需要按照该地点范围内4公里范围内查询酒店列表，排序按照默认推荐排序规则；|
| 关键词 | keyword | String | 否 | 多个关键词时，用" "空格隔开|
| 价格范围 | prices | String | 否 | 1.  传固定值时，例如300，接口按照250-350的范围取值；左-50，右+50。2.  传范围时，例如300-500，接口按照300-500的范围取值； |
| 每页条数 | pageSize | Integer | 否 | 默认配置条数 |
| 页码 | pageNum | Integer | 否 | 默认1 |


##### 请求示例
```json
{
	"cityName": "南京",
	"pageSize": 32
}
```

##### 返回参数

| 字段名称 | 字段编码 | 字段类型 | 非空 | 说明 |
| --- | --- | --- | --- | --- |
|是否成功|success|Boolean|是|
|状态码|errorCode|Integer|是|
|返回结果|data|Data|否||

- Data

| 字段名称 | 字段编码 | 字段类型 | 非空 | 说明 |
| --- | --- | --- | --- | --- |
|快照id	|queryId	|String	|是	|快照id 首次查询返回|
|总页数	|totalPageNum	|Integer|	是	 |
|数量	|count	|Integer	|是	 ||
|套餐列表|rows|List\<PackageHotel\>|否|

- PackageHotel

| 字段名称 | 字段编码 | 字段类型 | 非空 |   |
| --- | --- | --- | --- | --- |
| 首图 | firstPic | String | 否 |   |
| 行程天数 | journeyNum | Integer | 否 |   |
| 套餐id | packageId | String | 否 |   |
| 套餐价格 | packagePrice | Double | 否 |   |
| 点评分数 | commentScore | Double | 否 |   |
| 酒店id | hotelId | Long | 是 |   |
| 酒店名称 | hotelName | String | 否 |   |
| 房型id | roomId | Long | 否 |   |
| 房型名称 | roomName | String | 否 |   |
| 入住日期 | checkIn | String | 否 |   |
| 离店日期 | checkOut | String | 否 |   |
| 星级描述 | starName | String | 否 |   |
| 城市code | cityCode | Integer | 否 |   |
| 城市名称 | cityName | String | 否 |   |
| 套餐名称 | packageName | String | 否 |   |
| 套餐子项 | packageItemList | List\<PackageItem\> | 否 |   |

- PackageItem

| 字段名称 | 字段编码 | 字段类型 | 非空 | 说明 |
| --- | --- | --- | --- | --- |
|套餐子项类型|	itemType|	String	|否	|BREAKFAST/OTHERS...|
|套餐子项名称|	itemName|	String	|否	|早餐/芳草地首日精美欢迎水果|
|套餐子项数量	|quantity	|Integer	|否	|数量|

##### 返回示例 

```json
{
	"success": true,
	"errorCode": 590000,
	"data": {
		"queryId": "8E6789E41BEAC627",
		"totalPageNum": 2,
		"count": 16,
		"rows": [
			{
				"firstPic": "http://m.tuniucdn.com/fb3/s1/2n9c/2ec6KFiTPiWsnTbLaSMotfhtBo7E.jpg",
				"journeyNum": 2,
				"packageId": "2107327678",
				"packagePrice": 778.0,
				"commentScore": 4.0,
				"hotelId": 2073755245,
				"hotelName": "南京高淳域见芳草地度假酒店",
				"roomId": 2074237382,
				"roomName": "芳草地大床房",
				"checkIn": "2024-12-22",
				"starName": "四钻",
				"cityCode": "1602",
				"cityName": "南京",
				"packageName": "芳草地大床房3天2晚套餐",
				"checkOut": "2024-12-24",
				"packageItemList": [
					{
						"itemType": "BREAKFAST",
						"itemName": "早餐",
						"quantity": 2
					},
					{
						"itemType": "OTHERS",
						"itemName": "芳草地首日精美欢迎水果",
						"quantity": 1
					}
				]
			}
		]
	}
}
```



