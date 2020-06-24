package com.example.yyw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/23 9:40
 * @Description
 */
@RestController
@RequestMapping("/example")
public class ExampleController {

    @RequestMapping("/getParam")
    public Object getParam(HttpServletRequest httpServletRequest){
        return httpServletRequest.getParameterMap();
    }
}
