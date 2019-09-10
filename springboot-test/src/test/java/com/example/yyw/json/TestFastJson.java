package com.example.yyw.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.yyw.json.vo.HotelsVo;
import org.junit.Test;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/8 16:04
 * @describe
 */
public class TestFastJson {

    @Test
    public void test_fastjson_1(){
        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"hotelList\": [\n" +
                "            {\n" +
                "                \"images\": [\n" +
                "                    {\n" +
                "                        \"imageUrl\": \"http://pic.cnbooking.net:10541/CT/14/75090efe40eecfc0.jpg\",\n" +
                "                        \"imageId\": \"622057\",\n" +
                "                        \"imageName\": \"酒店外景\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"imageUrl\": \"http://pic.cnbooking.net:10541/CT/14/87f952b60356e633.jpg\",\n" +
                "                        \"imageId\": \"622058\",\n" +
                "                        \"imageName\": \"酒店外景\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"services\": [\n" +
                "                    {\n" +
                "                        \"serviceId\": \"17\",\n" +
                "                        \"serviceName\": \"电脑\",\n" +
                "                        \"groupId\": \"7\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"19\",\n" +
                "                        \"serviceName\": \"中央空调\",\n" +
                "                        \"groupId\": \"7\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"24\",\n" +
                "                        \"serviceName\": \"吹风机\",\n" +
                "                        \"groupId\": \"7\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"34\",\n" +
                "                        \"serviceName\": \"租车服务\",\n" +
                "                        \"groupId\": \"6\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"48\",\n" +
                "                        \"serviceName\": \"叫醒服务\",\n" +
                "                        \"groupId\": \"8\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"64\",\n" +
                "                        \"serviceName\": \"棋牌室\",\n" +
                "                        \"groupId\": \"12\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"71\",\n" +
                "                        \"serviceName\": \"免费洗漱用品\",\n" +
                "                        \"groupId\": \"7\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"93\",\n" +
                "                        \"serviceName\": \"邮政服务\",\n" +
                "                        \"groupId\": \"8\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"99\",\n" +
                "                        \"serviceName\": \"客房WIFI\",\n" +
                "                        \"groupId\": \"4\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"18\",\n" +
                "                        \"serviceName\": \"电热水壶\",\n" +
                "                        \"groupId\": \"7\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"22\",\n" +
                "                        \"serviceName\": \"房间内高速上网\",\n" +
                "                        \"groupId\": \"4\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"45\",\n" +
                "                        \"serviceName\": \"商务中心\",\n" +
                "                        \"groupId\": \"9\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"63\",\n" +
                "                        \"serviceName\": \"免费赠送地图\",\n" +
                "                        \"groupId\": \"8\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"91\",\n" +
                "                        \"serviceName\": \"多种规格电源插座\",\n" +
                "                        \"groupId\": \"7\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"serviceId\": \"94\",\n" +
                "                        \"serviceName\": \"行李存放服务\",\n" +
                "                        \"groupId\": \"8\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"landmark\": [\n" +
                "                    {\n" +
                "                        \"landid\": \"\",\n" +
                "                        \"landName\": \"大新站\",\n" +
                "                        \"distance\": \"0.46\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"\",\n" +
                "                        \"landName\": \"鲤鱼门站\",\n" +
                "                        \"distance\": \"1.633\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"\",\n" +
                "                        \"landName\": \"深圳北站\",\n" +
                "                        \"distance\": \"18.24\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"\",\n" +
                "                        \"landName\": \"青青世界\",\n" +
                "                        \"distance\": \"4.88\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"\",\n" +
                "                        \"landName\": \"桃园\",\n" +
                "                        \"distance\": \"0.87\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"\",\n" +
                "                        \"landName\": \"深圳湾公园\",\n" +
                "                        \"distance\": \"6.71\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"\",\n" +
                "                        \"landName\": \"桃园站\",\n" +
                "                        \"distance\": \"0.638\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"A120914\",\n" +
                "                        \"landName\": \"南山区\",\n" +
                "                        \"distance\": \"\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"490193\",\n" +
                "                        \"landName\": \"桃园站\",\n" +
                "                        \"distance\": \"0.638\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"490194\",\n" +
                "                        \"landName\": \"深圳福田高铁站\",\n" +
                "                        \"distance\": \"16.04\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225557\",\n" +
                "                        \"landName\": \"大新站\",\n" +
                "                        \"distance\": \"0.46\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225558\",\n" +
                "                        \"landName\": \"鲤鱼门站\",\n" +
                "                        \"distance\": \"1.633\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225559\",\n" +
                "                        \"landName\": \"深圳大学\",\n" +
                "                        \"distance\": \"1.062\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225560\",\n" +
                "                        \"landName\": \"华侨城\",\n" +
                "                        \"distance\": \"7.08\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225561\",\n" +
                "                        \"landName\": \"科技园\",\n" +
                "                        \"distance\": \"2.37\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225562\",\n" +
                "                        \"landName\": \"深圳宝安国际机场\",\n" +
                "                        \"distance\": \"17.87\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225563\",\n" +
                "                        \"landName\": \"深圳北站\",\n" +
                "                        \"distance\": \"18.24\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225564\",\n" +
                "                        \"landName\": \"市中心\",\n" +
                "                        \"distance\": \"17.31\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225565\",\n" +
                "                        \"landName\": \"青青世界\",\n" +
                "                        \"distance\": \"4.88\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225566\",\n" +
                "                        \"landName\": \"深圳湾公园\",\n" +
                "                        \"distance\": \"6.71\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225567\",\n" +
                "                        \"landName\": \"大新\",\n" +
                "                        \"distance\": \"0.72\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"landid\": \"225568\",\n" +
                "                        \"landName\": \"桃园\",\n" +
                "                        \"distance\": \"0.87\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"distance\": 3.0,\n" +
                "                \"isCollect\": 0,\n" +
                "                \"countryId\": \"0001\",\n" +
                "                \"provinceId\": \"2000\",\n" +
                "                \"cityId\": \"2003\",\n" +
                "                \"affiliatedGroupId\": \"\",\n" +
                "                \"score\": \"4.10\",\n" +
                "                \"startPrice\": \"150\",\n" +
                "                \"countryName\": \"中国大陆\",\n" +
                "                \"provinceName\": \"广东\",\n" +
                "                \"postCode\": \"\",\n" +
                "                \"email\": \"\",\n" +
                "                \"startBusinessDate\": \"2014-10-01\",\n" +
                "                \"repairdate\": \"2016-04-01\",\n" +
                "                \"star\": \"012014\",\n" +
                "                \"hotelName\": \"提子酒店(深圳南新路店)(原君恺酒店)\",\n" +
                "                \"starName\": \"三星级\",\n" +
                "                \"intro\": \"提子酒店（深圳南新路店）（原君恺酒店）地处前海商业中心区，为南山商业圈核心部位，毗邻南山区政府、深圳高新技术产业园区、蛇口港务区、前海物流区、南山商业文化中心、火车西站、大新地铁站D出口等，位置极佳。&lt;br&gt;&lt;br&gt;与著名的世界之窗、华侨城、锦绣中华、欢乐谷、科技园区、深圳湾口岸、蛇口港码头、广深高速公路、深港西部通道、滨海大道、火车西站、深圳大学、南山医院等相距均在15分钟车程范围内，交通便利。&lt;br&gt;&lt;br&gt;周围家乐福、人人乐、华润万家、天虹百货、南山市场、迪拜量贩KTV等购物和娱乐场所环绕，出行购物及美食极为方便。&lt;br&gt;&lt;br&gt;酒店内有多种类型的舒适客房，同时提供无线光纤上网、24小时客房管家等服务，环境优雅舒适，是到鹏城商务、旅游、会议、休闲下榻的上选。\",\n" +
                "                \"guide\": \"\",\n" +
                "                \"hotelId\": \"120914\",\n" +
                "                \"cityName\": \"深圳\",\n" +
                "                \"lat\": \"22.529958\",\n" +
                "                \"lon\": \"113.918974\",\n" +
                "                \"telephone\": \"0755-88821588\",\n" +
                "                \"currency\": \"CNY\",\n" +
                "                \"address\": \"深圳南新路2054号（南新大厦）,近工商银行\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"pageInfo\": {\n" +
                "            \"pageNum\": 1,\n" +
                "            \"pageSize\": 1,\n" +
                "            \"totalRecord\": 473,\n" +
                "            \"totalPage\": 473\n" +
                "        },\n" +
                "        \"hotelCount\": 473\n" +
                "    },\n" +
                "    \"resultCode\": \"1\",\n" +
                "    \"resultMessage\": \"操作成功\"\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(json);
        HotelsVo hotelsVo = JSON.parseObject(jsonObject.getJSONObject("data").toJSONString(), new TypeReference<HotelsVo>(){});
        System.out.println(hotelsVo);
    }

}
