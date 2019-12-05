package com.example.yyw.controller;

import com.example.yyw.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/24 11:40
 */
@RestController
@RequestMapping("async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/test1")
    public String test1(){
        return asyncService.test1();
    }

    @RequestMapping("/test2")
    public void method_test2(){
        asyncService.tes2();
    }

    @RequestMapping("/test3")
    public void method_test3(){
        asyncService.test3();
    }
}

