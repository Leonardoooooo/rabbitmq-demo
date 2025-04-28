---
title: 酒店详情
lang: zh-CN
outline: deep
---


    
##### 简要描述

- 酒店详情查询接口

##### 请求URL
- https://platform.tuniu.cn/hts/agent/hotel/detail
  
##### 请求方式
- POST 

##### 请求参数

|  字段名称 | 字段编码 | 字段类型 | 是否必填 | 说明 |
| --- | --- | --- | --- | --- |
| 酒店id | hotelId | Long | 是| 与 hotelName <br/>必传一个 |
| 酒店名称 | hotelName | String |是 | 与 hotelId <br/>必传一个|
| 入住日期 | checkIn | String | 否 | 默认第二天 |
| 离店日期 | checkOut | String | 否 | 默认第三天 |
| 房间数量 | roomNum | Integer | 否 | 默认1间房 |
| 成人数量 | adultNum | Integer | 否 | 默认2成人 |
| 儿童数量 | childNum | Integer | 否 | 默认0儿童 |
| 儿童年龄 | childAges | List\<Integer\> | 否 | 对应儿童数 |
| 套餐 | onlyPackagePrice | String | 否 | 默认false, 为true时<br/>只返回有套餐的报价<br/>（酒套使用） |

##### 请求示例
```json
{
	"hotelId": 84870,
	"hotelName": "三亚国光豪生度假酒店",
	"checkIn": "2025-03-18",
	"checkOut": "2025-03-19",
	"roomNum": 1,
	"adultNum": 2,
	"childNum": 1,
	"childAges": [
		2
	],
	"onlyPackagePrice": false
}
```

##### 返回参数
| 字段名称 | 字段编码 | 字段类型 | 非空 | 说明 |
| --- | --- | --- | --- | --- |
| 酒店id | hotelid | Long | 是 |   |
| 酒店名称 | hotelName | String | 是 |   |
| 入住日期 | checkIn | String | 否 |   |
| 离店日期 | checkOut | String | 否 |   |
| 房间数量 | roomNum | Integer | 否 |   |
| 成人数 | adultNum | Integer | 否 |   |
| 儿童数 | childNum | Integer | 否 |   |
| 是否国内酒店 | domestic | Boolen | 是 |   |
| 房间 | rooms | List\<Room\> | 否 | 房型及房型报价 |

- Room

| 字段名称 | 字段编码 | 字段类型 | 非空 | 说明 |
| --- | --- | --- | --- | --- |
| 房型id | roomId | Long | 是 |   |
| 房型名称 | roomName | String | 是 |   |
| 房间面积 | area | String | 否 |   |
| 房间楼层 | floor | String | 否 |   |
| 床型 | bedTypeName | String | 否 |   |
| 床型大小 | bedSize | String | 否 |   |
| 窗型 | window | String | 否 |   |
| 吸烟情况 | smoking | String | 否 |   |
| 房间数量 | roomCount | Integer | 否 |   |
| 房间图片 | firstPic | String | 否 |   |
| 报价信息 | ratePlans | List\<RatePlan\> | 是 |   |

RatePlan

| 字段名称 | 字段编码 | 字段类型 | 非空 | 说明 |
| --- | --- | --- | --- | --- |
| 价格 | rmbPrices | String | 是 |   |
| 补充验价接口参数 | preBookParam | String | 是 | /hts/agent/hotel/book/param补充验价接口需要的参数 实现预定页跳转 |
| 报价名称 | ratePlanName | String | 是 |   |
| 报价id | vendorRatePlanId | String | 是 |   |
| 餐食 | mealText | String | 否 |   |
| 取消描述 | cancelDesc | String | 否 |   |
| 取消规则类型 | cancelText | String | 否 |   |
| 报价库存 | count | Integer | 是 |   |
| 套餐信息 | packageInfo | PackageInfo | 否 |   |

PackageInfo

| 字段名称 | 字段编码 | 字段类型 | 非空 | 说明 |
| --- | --- | --- | --- | --- |
|套餐名称|packageName|String|否|
|套餐描述|packageDesc|String|否|
|套餐子项明细|packageItemList|List\<PackageItem\> | 否  ||

PackageItem

| 字段名称 | 字段编码 | 字段类型 | 非空 | 说明 |
| --- | --- | --- | --- | --- |
| 套餐子项类型 | itemTypeName | String | 否 |   |
| 套餐子项名称 | itemName | String | 否 |   |
| 套餐有效期说明 | itemValidDesc | String | 否 |   |
| 赠送方式 | giveType | String | 否 |   |
| 套餐使用方式 | entryMode | String | 否 |   |
| 使用规则 | useRuleList | List\<String\> | 否 |   |
| 使用地址 | saddress | String | 否 |   |

##### 返参示例
```json
 {
	"successCode": true,
	"msg": "OK",
	"hotelId": 84870,
	"hotelName": "三亚国光豪生度假酒店",
	"checkIn": "2025-03-13",
	"checkOut": "2025-03-14",
	"roomNum": 1,
	"adultNum": 2,
	"childNum": 0,
	"domestic": true,
	"rooms": [
		{
			"roomId": 1890845876,
			"roomName": "休闲双床房",
			"area": "57㎡",
			"floor": "1-7层",
			"bedTypeName": "未知床型",
			"bedSize": "",
			"window": "有窗",
			"smoking": "不能抽烟",
			"roomCount": 30,
			"firstPic": "https://m.tuniucdn.com/fb3/s1/2n9c/boimw2UkUmBEfobUMEE2QhTT2VV_w180_h180_c1_t0_q70.jpg",
			"ratePlans": [
				{
					"rmbPrices": "700.0",
					"preBookParam": "272867A3C15C82204D11FF103032BB6B",
					"packageInfo": {
						"packageName": "2张100元SPA代金券+章鱼部落乐玩家活动+铂爵旅拍+cdf中免礼遇",
						"packageDesc": null,
						"packageItemList": [
							{
								"itemTypeName": "礼品",
								"itemName": "cdf中免礼遇",
								"itemValidDesc": "离店后3日失效",
								"giveType": "只赠送一次",
								"entryMode": "凭确认短信直接入园",
								"useRuleList": [
									"2名成人，"
								],
								"saddress": ""
							}
						]
					},
					"ratePlanName": "(官方直销网红泳池带娃爱住)",
					"vendorRatePlanId": "4472942_2680982_757370985",
					"mealText": "无早餐",
					"cancelDesc": "如未入住或取消及提前离店，途牛将收取¥700.0支付酒店。",
					"cancelText": "不可取消",
					"count": 99
				}
			]
		}
	]
}
```





