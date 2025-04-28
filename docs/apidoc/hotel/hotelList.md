---
title: 酒店列表
lang: zh-CN
outline: deep
---

    
##### 简要描述

- 酒店列表查询接口

##### 请求URL
- ` https://openapi.tuniu.cn/hts/agent/poi/hotel/list`
  
##### 请求方式
- POST 

##### 参数
|参数名|参数描述|类型|是否必填|备注|
|:----    |:---|:----- |-----   |-----|
|cityName |城市   |string |是  |cityName有值时，查询cityName下的poiName位置，默认上海|
|poiName |用户输入的poi名称    |string | 否  |用户输入的poi名称|
|checkIn     |入住日期    |string |否  | 优先客户指定 未指定时看下方具体逻辑，默认明天|
|checkOut     |离店日期    |string | 否  |优先客户指定 未指定时默认入住日期+1|
|adultNum     |入住成人数    |Integer | 否  |优先客户指定 未指定时默认2|
|childNum     |入住儿童数    |Integer | 否  |优先客户指定 未指定时默认0|
|checkOut     |离店日期    |string | 否  |优先客户指定 未指定时默认入住日期+1|
|childAges     |儿童年龄    |List\<Integer\> | 否  |儿童年龄，list size与儿童数一致|
|keyword     |关键词    |string | 否  |关键词包含星级、品牌、酒店特色、设施、名称等|
|prices     |价格范围    |string | 否  |传固定值时，取左-50，右+50（例如300，接口按照250-350的范围取值）；传范围时，例如300-500，接口按照300-500的范围取值；|
|userLocation     |用户定位    |Location | 否  |用户定位所在城市和用户经纬度| 

- Location

|参数名|参数描述|类型|是否必填|备注|
|:----    |:---|:----- |-----   |-----|
|cityCode     |城市code    |Integer | 否  |用户所在城市code|
|cityName     |城市名    |Integer | 否  |用户所在城市名称|
|lng|用户经度|Double|否|用户定位经度|
|lat|用户纬度|Double|否|用户定位纬度|

##### 请求示例

```json
{
	"toolName": "Nlowest_price_hotellist",
	"cityName": "入住城市",
	"checkIn": "入住日期",
	"checkOut": "离店日期",
	"adultNum": "成人数",
	"childNum": "儿童数",
	"poiName": "poi地点",
	"prices": "300-500",
	"keyword": "其他信息",
	"userLocation": {
		"cityCode": 2500,
		"cityName": "上海",
		"lng": 2.123141,
		"lat": 2.123141
	}
}
```

##### 返回参数
|参数名|参数描述|类型|是否必填|备注|
|:----    |:---|:----- |-----   |-----|
|successCode     |查询成功    |Boolean | 是  |用户所在城市code|
|queryId     |快照id    |String | 是  |首次查询返回|
|totalPageNum|总页数|Integer|是|首次查询返回|
|data|酒店结果列表|List\<Hotel\>|否|||

- Hotel

|参数名|参数描述|类型|是否必填|备注|
|:----    |:---|:----- |-----   |-----|
|hotelId     |酒店id    |Long | 是  ||
|chineseName     |酒店名称    |String | 是  ||
|starName     |酒店星级    |String | 否  |舒适型 豪华型|
|firstPic     |酒店图片    |String | 否  |酒店首图地址|
|address     |酒店地址    |String | 否  ||
|cityName     |城市    |String | 否  ||
|cityCode     |城市 code   |Integer | 否  ||
|cityType     |城市类型    |Integer | 否  |城市code类型 0：正常编码 1：城市tag ex:沙巴 收藏 跳转页面用|
|domestic     |国内酒店    |Boolean | 否  |港澳台除外|
|checkIn     |入住日期 |String | 否  |收藏 跳转页面用|
|checkOut     |离店日期   |String | 否  |收藏 跳转页面用|
|business     |酒店商圈   |String | 否  |exp：新街口地区|
|brandName     |酒店品牌    |String | 否  |exp：桔子酒店|
|commentScore     |酒店评分    |Double | 否|exp：5.0|
|lowestPrice     |起价    |Double | 否  |含税价|
|commentDigest     |一句话点评    |String | 否  |exp：新街口中心，便捷出行！|
|meal     |餐食    |String | 否  |exp：无早餐|
|refund     |取消规则    |String | 否  |exp：如未入住或取消及提前离店，途牛将收取¥426.0支付酒店。|
|roomId     |起价房型  |Long | 否  ||
|roomName     |房型名称    |String | 否  |exp：高级大床房|
|roomArea     |房型面积   |String | 否  |exp：17-18㎡|
|roomWindow     |房型窗户    |String | 否  |exp：有窗|
|distance     |距离    |String |否  |距用户直线/规划距离|


##### 返回示例 

```json
{
  "queryId": "1057014089580800",
  "successCode": true,
  "totalPageNum": 2,
  "data": [
    {
      "hotelId": 1650247895,
      "domestic": true,
      "firstPic": "http://m.tuniucdn.com/fb3/s1/2n9c/3krw1PgyPV6dfigrYHJSNRC6pysV.jpg",
      "chineseName": "桔子酒店（南京新街口王府大街店）",
      "starName": "舒适型",
      "address": "南台巷30-32号（近大洋百货、金鹰国际中心）",
      "cityName": "南京",
      "cityCode": 1602,
      "cityType": 0,
      "checkIn": "2025-03-16",
      "checkOut": "2025-03-17"
      "business": "新街口地区",
      "lowestPrice": 426,
      "commentScore": 4.4,
      "commentDigest": "新街口中心，便捷出行！",
      "brandName": "桔子酒店",
      "meal": "无早餐",
      "refund": "如未入住或取消及提前离店，途牛将收取¥426.0支付酒店。",
      "roomId": 1650247899,
      "roomName": "高级大床房",
      "roomArea": "17-18㎡",
      "roomWindow": "有窗",
      "distance": "距您直线距离2.3公里"
    }
  ]
}
```



