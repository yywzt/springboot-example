package com.example.yyw.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.yyw.constant.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author yanzhitao@xiaomalixing.com
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
}
