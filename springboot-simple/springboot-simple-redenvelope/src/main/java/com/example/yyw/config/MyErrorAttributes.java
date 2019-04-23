package com.example.yyw.config;

import com.example.yyw.constant.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author yw
 * @create 2018-06-22 16:05
 * @description: 自定义的ErrorAttributes 自定义返回的json串
 */
//@Component
@Slf4j
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        //获取系统已有的提示信息 timestamp status 等...
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        //从request中获取自定义的数据
        ResponseData info = (ResponseData) webRequest.getAttribute("info",0);
        map.put("info",info);
        log.error("error: {}",map);
        return map;
    }
}
