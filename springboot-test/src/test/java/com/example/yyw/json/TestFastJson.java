package com.example.yyw.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.yyw.json.vo.HotelsVo;
import com.example.yyw.json.vo.MeiTuanVo;
import com.example.yyw.json.vo.otherIf.CinemasListVo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test_fastjson_2(){
        String json = "{\"data\":{\"cinemas\":[{\"cinemaId\":\"38973\",\"cinemaName\":\"深圳百老汇电影中心（IMAX万象天地店）\",\"cinemaCode\":\"24241\",\"spCode\":\"sp1006\",\"countyCode\":\"440305\",\"countyName\":\"南山区\",\"address\":\"粤海街道华润万象天地L5层SL562号店铺\",\"longitude\":\"113.95608\",\"latitude\":\"22.541945\",\"imgUrl\":\"http://www.carbuyin.net/sl/filePath/e574db52-3762-4e4d-be1d-dc8c3fc7acd8.png\",\"mobile\":\"\",\"facilitys\":\"[]\",\"terminal\":\"21\",\"distance\":\"0.0\",\"buyTimeLimit\":0,\"newBuyTimeLimit\":0,\"isCollect\":0},{\"cinemaId\":\"23459\",\"cinemaName\":\"麦希中影南方影城（深大店）\",\"cinemaCode\":\"15785\",\"spCode\":\"sp1006\",\"countyCode\":\"440305\",\"countyName\":\"南山区\",\"address\":\"深南大道深大地铁站A3出口科技园文化广场3楼\",\"longitude\":\"113.94326\",\"latitude\":\"22.54062\",\"imgUrl\":\"http://www.carbuyin.net/sl/filePath/e574db52-3762-4e4d-be1d-dc8c3fc7acd8.png\",\"mobile\":\"\",\"facilitys\":\"[]\",\"terminal\":\"20\",\"distance\":\"1.0\",\"buyTimeLimit\":0,\"newBuyTimeLimit\":0,\"isCollect\":0},{\"cinemaId\":\"34254\",\"cinemaName\":\"太平洋影城（深圳京基百纳店）\",\"cinemaCode\":\"4579\",\"spCode\":\"sp1007\",\"countyCode\":\"440305\",\"countyName\":\"南山区\",\"address\":\"南山区白石路与石洲中路交汇处京基百纳广场3楼（中信红树湾旁）\",\"longitude\":\"113.968861\",\"latitude\":\"22.531093\",\"imgUrl\":\"http://www.carbuyin.net/sl/filePath/e574db52-3762-4e4d-be1d-dc8c3fc7acd8.png\",\"mobile\":\"\",\"facilitys\":\"[]\",\"terminal\":\"3\",\"distance\":\"1.0\",\"buyTimeLimit\":0,\"newBuyTimeLimit\":0,\"isCollect\":0},{\"cinemaId\":\"23432\",\"cinemaName\":\"中影德金影城（南山店）\",\"cinemaCode\":\"16597\",\"spCode\":\"sp1006\",\"countyCode\":\"440305\",\"countyName\":\"南山区\",\"address\":\"南头街道艺园东路缤纷年华家园商业裙楼301A-2号\",\"longitude\":\"113.92773\",\"latitude\":\"22.542604\",\"imgUrl\":\"http://www.carbuyin.net/sl/filePath/e574db52-3762-4e4d-be1d-dc8c3fc7acd8.png\",\"mobile\":\"\",\"facilitys\":\"[]\",\"terminal\":\"21\",\"distance\":\"2.0\",\"buyTimeLimit\":0,\"newBuyTimeLimit\":0,\"isCollect\":0},{\"cinemaId\":\"25386\",\"cinemaName\":\"深圳保利国际影城南山店\",\"cinemaCode\":\"143\",\"spCode\":\"sp1006\",\"countyCode\":\"440305\",\"countyName\":\"南山区\",\"address\":\"文心五路保利文化广场B区3楼（文心五路口）\",\"longitude\":\"113.937256\",\"latitude\":\"22.517458\",\"imgUrl\":\"http://www.carbuyin.net/sl/filePath/e574db52-3762-4e4d-be1d-dc8c3fc7acd8.png\",\"mobile\":\"\",\"facilitys\":\"[]\",\"terminal\":\"21\",\"distance\":\"2.0\",\"buyTimeLimit\":0,\"newBuyTimeLimit\":0,\"isCollect\":0},{\"cinemaId\":\"34247\",\"cinemaName\":\"中影国际影城深圳益田假日广场店\",\"cinemaCode\":\"4300\",\"spCode\":\"sp1007\",\"countyCode\":\"440305\",\"countyName\":\"南山区\",\"address\":\"南山区益田假日广场L3－8中影益田假日影城\",\"longitude\":\"113.974045\",\"latitude\":\"22.537839\",\"imgUrl\":\"http://www.carbuyin.net/sl/filePath/e574db52-3762-4e4d-be1d-dc8c3fc7acd8.png\",\"mobile\":\"\",\"facilitys\":\"[]\",\"terminal\":\"3\",\"distance\":\"2.0\",\"buyTimeLimit\":0,\"newBuyTimeLimit\":0,\"isCollect\":0},{\"cinemaId\":\"34250\",\"cinemaName\":\"深圳海岸影城\",\"cinemaCode\":\"4345\",\"spCode\":\"sp1007\",\"countyCode\":\"440305\",\"countyName\":\"南山区\",\"address\":\"南山区海岸城购物中心三楼\",\"longitude\":\"113.9359684509\",\"latitude\":\"22.5168491341\",\"imgUrl\":\"http://www.carbuyin.net/sl/filePath/e574db52-3762-4e4d-be1d-dc8c3fc7acd8.png\",\"mobile\":\"\",\"facilitys\":\"[]\",\"terminal\":\"3\",\"distance\":\"2.0\",\"buyTimeLimit\":0,\"newBuyTimeLimit\":0,\"isCollect\":0},{\"cinemaId\":\"34253\",\"cinemaName\":\"太平洋影城（深圳天利名城店）\",\"cinemaCode\":\"4576\",\"spCode\":\"sp1007\",\"countyCode\":\"440305\",\"countyName\":\"南山区\",\"address\":\"南山区天利名城5楼、6楼（海岸城对面）\",\"longitude\":\"113.935366\",\"latitude\":\"22.518437\",\"imgUrl\":\"http://www.carbuyin.net/sl/filePath/e574db52-3762-4e4d-be1d-dc8c3fc7acd8.png\",\"mobile\":\"\",\"facilitys\":\"[]\",\"terminal\":\"3\",\"distance\":\"2.0\",\"buyTimeLimit\":0,\"newBuyTimeLimit\":0,\"isCollect\":0},{\"cinemaId\":\"34280\",\"cinemaName\":\"深圳华夏星光国际影城\",\"cinemaCode\":\"9492\",\"spCode\":\"sp1007\",\"countyCode\":\"440305\",\"countyName\":\"南山区\",\"address\":\"南山区海德二道南山书城7楼\",\"longitude\":\"113.9296163444\",\"latitude\":\"22.5193511351\",\"imgUrl\":\"http://www.carbuyin.net/sl/filePath/e574db52-3762-4e4d-be1d-dc8c3fc7acd8.png\",\"mobile\":\"\",\"facilitys\":\"[]\",\"terminal\":\"3\",\"distance\":\"2.0\",\"buyTimeLimit\":0,\"newBuyTimeLimit\":0,\"isCollect\":0},{\"cinemaId\":\"37656\",\"cinemaName\":\"万象影城（深圳湾万象城旗舰店）\",\"cinemaCode\":\"57523\",\"spCode\":\"sp1007\",\"countyCode\":\"440305\",\"countyName\":\"南山区\",\"address\":\"南山区科苑南路2888号华润深圳湾万象城L3层\",\"longitude\":\"113.946681\",\"latitude\":\"22.516271\",\"imgUrl\":\"http://www.carbuyin.net/sl/filePath/e574db52-3762-4e4d-be1d-dc8c3fc7acd8.png\",\"mobile\":\"\",\"facilitys\":\"[]\",\"terminal\":\"3\",\"distance\":\"2.0\",\"buyTimeLimit\":0,\"newBuyTimeLimit\":0,\"isCollect\":0}],\"pageInfo\":{\"pageNum\":1,\"pageSize\":10,\"totalRecord\":36,\"totalPage\":4}},\"resultCode\":\"1\",\"resultMessage\":\"操作成功\"}";
        JSONObject jsonObject = JSON.parseObject(json);
        CinemasListVo cinemasListVo = JSON.parseObject(jsonObject.getJSONObject("data").toJSONString(), new TypeReference<CinemasListVo>(){});
        System.out.println(cinemasListVo);
    }
}
