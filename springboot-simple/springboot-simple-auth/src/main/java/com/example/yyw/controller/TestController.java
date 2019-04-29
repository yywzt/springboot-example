package com.example.yyw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/29 22:54
 * @describe
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")
    public String test1(){
        return "test1";
    }

}
