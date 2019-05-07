package com.example.yyw.xmly.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.yyw.xmly.config.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/7 23:54
 * @describe
 */
@Slf4j
@RestController
@RequestMapping("/testFastJson")
public class TestFastJson {

    @RequestMapping("/test1")
    public String test1(){
        String s = JSON.toJSONString(ResponseData.success(), SerializerFeature.WriteMapNullValue);
        log.info("s : {}",s);
        return s;
    }
    @RequestMapping("/test2")
    public Object test2(){
        Object o = JSON.toJSON(ResponseData.success());
        log.info("object : {}",o);
        return o;
    }
    @RequestMapping("/test3")
    public String test3(){
        String s = JSONObject.toJSONString(ResponseData.success(),SerializerFeature.WriteMapNullValue);
        log.info("s : {}",s);
        return s;
    }
    @RequestMapping("/test4")
    public Object test4(){
        Object o = JSONObject.toJSON(ResponseData.success());
        log.info("object : {}",o);
        return o;
    }
}
