package com.example.yyw.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.yyw.constant.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/7 23:32
 * @describe
 */
@Slf4j
public class FastJsonTest {

    private static final String json = "{\"data\":[{\"kind\":\"tag\",\"tag_name\":\"悬疑\"}],\"resultCode\":\"1\",\"resultMessage\":\"操作成功\"}";

    @Test
    public void test1(){
        JSONObject jsonObject = JSON.parseObject(json);
        log.info("jsonObject: {}",jsonObject);
        Object data = jsonObject.get("data");
        log.info("data: {}",data);
    }

    @Test
    public void test2(){
        toJson();
        toJsonObject();
    }

    public static String toJson(){
        ResponseData success = ResponseData.success();
        String s = JSON.toJSONString(success, SerializerFeature.WriteMapNullValue);
        log.info("s : {}",s);
        return s;
    }
    public static Object toJsonObject(){
        ResponseData success = ResponseData.success();
        Object object = JSON.toJSON(success);
        log.info("object : {}",object);
        return object;
    }

    @Test
    public void test3() throws JSONException {
        String response = "{\n" +
                "    \"code\": \"10000\",\n" +
                "    \"charge\": false,\n" +
                "    \"remain\": 300000,\n" +
                "    \"msg\": \"查询成功\",\n" +
                "    \"result\": {\n" +
                "        \"status\": 0,\n" +
                "        \"msg\": \"ok\",\n" +
                "        \"result\": {\n" +
                "            \"trainno\": \"K968\",\n" +
                "            \"startstation\": \"张家界\",\n" +
                "            \"endstation\": \"北京\",\n" +
                "            \"type\": \"K\",\n" +
                "            \"date\": \"2019-05-09\",\n" +
                "            \"trainno12306\": \"\",\n" +
                "            \"typename\": \"快速\",\n" +
                "            \"list\": [\n" +
                "                {\n" +
                "                    \"sequenceno\": 1,\n" +
                "                    \"station\": \"张家界\",\n" +
                "                    \"day\": 1,\n" +
                "                    \"arrivaltime\": \"----\",\n" +
                "                    \"departuretime\": \"13:36\",\n" +
                "                    \"stoptime\": 0,\n" +
                "                    \"costtime\": 0,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 3,\n" +
                "                    \"station\": \"临澧\",\n" +
                "                    \"day\": 1,\n" +
                "                    \"arrivaltime\": \"15:22\",\n" +
                "                    \"departuretime\": \"15:25\",\n" +
                "                    \"stoptime\": 3,\n" +
                "                    \"costtime\": 0,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 23.5,\n" +
                "                    \"pricerw1\": 105.5,\n" +
                "                    \"priceyw1\": 69.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 4,\n" +
                "                    \"station\": \"常德\",\n" +
                "                    \"day\": 1,\n" +
                "                    \"arrivaltime\": \"15:56\",\n" +
                "                    \"departuretime\": \"16:10\",\n" +
                "                    \"stoptime\": 14,\n" +
                "                    \"costtime\": 34,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 29.5,\n" +
                "                    \"pricerw1\": 115.5,\n" +
                "                    \"priceyw1\": 75.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 5,\n" +
                "                    \"station\": \"益阳\",\n" +
                "                    \"day\": 1,\n" +
                "                    \"arrivaltime\": \"17:13\",\n" +
                "                    \"departuretime\": \"17:19\",\n" +
                "                    \"stoptime\": 6,\n" +
                "                    \"costtime\": 111,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 43.5,\n" +
                "                    \"pricerw1\": 135.5,\n" +
                "                    \"priceyw1\": 89.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 7,\n" +
                "                    \"station\": \"长沙\",\n" +
                "                    \"day\": 1,\n" +
                "                    \"arrivaltime\": \"18:30\",\n" +
                "                    \"departuretime\": \"18:50\",\n" +
                "                    \"stoptime\": 20,\n" +
                "                    \"costtime\": 0,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 54.5,\n" +
                "                    \"pricerw1\": 155.5,\n" +
                "                    \"priceyw1\": 100.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 8,\n" +
                "                    \"station\": \"岳阳\",\n" +
                "                    \"day\": 1,\n" +
                "                    \"arrivaltime\": \"20:13\",\n" +
                "                    \"departuretime\": \"20:17\",\n" +
                "                    \"stoptime\": 4,\n" +
                "                    \"costtime\": 103,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 75,\n" +
                "                    \"pricerw1\": 205,\n" +
                "                    \"priceyw1\": 133\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 9,\n" +
                "                    \"station\": \"武昌\",\n" +
                "                    \"day\": 1,\n" +
                "                    \"arrivaltime\": \"23:43\",\n" +
                "                    \"departuretime\": \"00:07\",\n" +
                "                    \"stoptime\": 24,\n" +
                "                    \"costtime\": 313,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 102,\n" +
                "                    \"pricerw1\": 274,\n" +
                "                    \"priceyw1\": 178\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 10,\n" +
                "                    \"station\": \"信阳\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"02:48\",\n" +
                "                    \"departuretime\": \"02:51\",\n" +
                "                    \"stoptime\": 3,\n" +
                "                    \"costtime\": 498,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 128.5,\n" +
                "                    \"pricerw1\": 348.5,\n" +
                "                    \"priceyw1\": 222.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 11,\n" +
                "                    \"station\": \"驻马店\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"03:51\",\n" +
                "                    \"departuretime\": \"03:54\",\n" +
                "                    \"stoptime\": 3,\n" +
                "                    \"costtime\": 561,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 138.5,\n" +
                "                    \"pricerw1\": 372.5,\n" +
                "                    \"priceyw1\": 238.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 12,\n" +
                "                    \"station\": \"漯河\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"05:01\",\n" +
                "                    \"departuretime\": \"05:04\",\n" +
                "                    \"stoptime\": 3,\n" +
                "                    \"costtime\": 631,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 148.5,\n" +
                "                    \"pricerw1\": 398.5,\n" +
                "                    \"priceyw1\": 254.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 13,\n" +
                "                    \"station\": \"临颍\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"05:30\",\n" +
                "                    \"departuretime\": \"05:33\",\n" +
                "                    \"stoptime\": 3,\n" +
                "                    \"costtime\": 660,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 148.5,\n" +
                "                    \"pricerw1\": 398.5,\n" +
                "                    \"priceyw1\": 254.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 14,\n" +
                "                    \"station\": \"许昌\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"05:50\",\n" +
                "                    \"departuretime\": \"05:53\",\n" +
                "                    \"stoptime\": 3,\n" +
                "                    \"costtime\": 680,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 152.5,\n" +
                "                    \"pricerw1\": 409.5,\n" +
                "                    \"priceyw1\": 261.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 15,\n" +
                "                    \"station\": \"长葛\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"06:08\",\n" +
                "                    \"departuretime\": \"06:12\",\n" +
                "                    \"stoptime\": 4,\n" +
                "                    \"costtime\": 698,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 152.5,\n" +
                "                    \"pricerw1\": 409.5,\n" +
                "                    \"priceyw1\": 261.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 16,\n" +
                "                    \"station\": \"郑州\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"07:25\",\n" +
                "                    \"departuretime\": \"07:40\",\n" +
                "                    \"stoptime\": 15,\n" +
                "                    \"costtime\": 775,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 156.5,\n" +
                "                    \"pricerw1\": 422.5,\n" +
                "                    \"priceyw1\": 268.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 17,\n" +
                "                    \"station\": \"新乡\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"09:09\",\n" +
                "                    \"departuretime\": \"09:21\",\n" +
                "                    \"stoptime\": 12,\n" +
                "                    \"costtime\": 879,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 168.5,\n" +
                "                    \"pricerw1\": 450.5,\n" +
                "                    \"priceyw1\": 288.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 18,\n" +
                "                    \"station\": \"安阳\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"10:26\",\n" +
                "                    \"departuretime\": \"10:37\",\n" +
                "                    \"stoptime\": 11,\n" +
                "                    \"costtime\": 956,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 177.5,\n" +
                "                    \"pricerw1\": 476.5,\n" +
                "                    \"priceyw1\": 304.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 19,\n" +
                "                    \"station\": \"邯郸\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"11:29\",\n" +
                "                    \"departuretime\": \"11:33\",\n" +
                "                    \"stoptime\": 4,\n" +
                "                    \"costtime\": 1019,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 180.5,\n" +
                "                    \"pricerw1\": 488.5,\n" +
                "                    \"priceyw1\": 310.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 20,\n" +
                "                    \"station\": \"邢台\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"12:04\",\n" +
                "                    \"departuretime\": \"12:13\",\n" +
                "                    \"stoptime\": 9,\n" +
                "                    \"costtime\": 1054,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 189.5,\n" +
                "                    \"pricerw1\": 504.5,\n" +
                "                    \"priceyw1\": 322.5\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 21,\n" +
                "                    \"station\": \"石家庄\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"13:24\",\n" +
                "                    \"departuretime\": \"13:30\",\n" +
                "                    \"stoptime\": 6,\n" +
                "                    \"costtime\": 1134,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 198,\n" +
                "                    \"pricerw1\": 530,\n" +
                "                    \"priceyw1\": 337\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 22,\n" +
                "                    \"station\": \"定州\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"14:17\",\n" +
                "                    \"departuretime\": \"14:29\",\n" +
                "                    \"stoptime\": 12,\n" +
                "                    \"costtime\": 1187,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 201,\n" +
                "                    \"pricerw1\": 542,\n" +
                "                    \"priceyw1\": 343\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 23,\n" +
                "                    \"station\": \"保定\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"15:06\",\n" +
                "                    \"departuretime\": \"15:10\",\n" +
                "                    \"stoptime\": 4,\n" +
                "                    \"costtime\": 1236,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 206,\n" +
                "                    \"pricerw1\": 556,\n" +
                "                    \"priceyw1\": 353\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 24,\n" +
                "                    \"station\": \"徐水\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"15:27\",\n" +
                "                    \"departuretime\": \"15:29\",\n" +
                "                    \"stoptime\": 2,\n" +
                "                    \"costtime\": 1257,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 0,\n" +
                "                    \"priceyz\": 213,\n" +
                "                    \"pricerw1\": 572,\n" +
                "                    \"priceyw1\": 363\n" +
                "                },\n" +
                "                {\n" +
                "                    \"sequenceno\": 25,\n" +
                "                    \"station\": \"北京\",\n" +
                "                    \"day\": 2,\n" +
                "                    \"arrivaltime\": \"17:28\",\n" +
                "                    \"departuretime\": \"17:28\",\n" +
                "                    \"stoptime\": 0,\n" +
                "                    \"costtime\": 1378,\n" +
                "                    \"distance\": 0,\n" +
                "                    \"isend\": 1,\n" +
                "                    \"priceyz\": 224,\n" +
                "                    \"pricerw1\": 598,\n" +
                "                    \"priceyw1\": 381,\n" +
                "                    \"costtimetxt\": \"22时58分\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(response);
        int code = jsonObject.getIntValue("code");
        if (10000 == code) {
            Map<String, Object> ret = new HashMap<>();
            JSONObject result = jsonObject.getJSONObject("result").getJSONObject("result");
            String number = result.getString("trainno");// 车次号
            String startStation = result.getString("startstation");// 起点站
            if (startStation.contains("乌鲁木齐")) {
                startStation = "乌鲁木齐";
            }
            String endStation = result.getString("endstation");// 终点站
            if (endStation.contains("乌鲁木齐")) {
                endStation = "乌鲁木齐";
            }

            JSONArray station_list = result.getJSONArray("list");
            List<Map<String, Object>> maps = new ArrayList<>();
            for (int i = 0; i < station_list.size(); i++) {
                JSONObject station = station_list.getJSONObject(i);
                String station_name = station.getString("station");//	站点名称
                String leave_time = station.getString("departuretime");//发车时间
                String arrived_time = station.getString("arrivaltime");//到达时间
//                String stay = station.get("stoptime").toString();//停留
                String stay = station.getString("stoptime");//停留
                Map<String, Object> station_map = new HashMap<>();
                station_map.put("station_name", station_name);
                station_map.put("arrived_time", arrived_time);
                station_map.put("leave_time", leave_time);
                station_map.put("stay", stay);
                maps.add(station_map);
            }
        }
    }
}
