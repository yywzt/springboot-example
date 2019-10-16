package com.example.yyw.controller;

import com.example.yyw.annotation.RepeatSubmit;
import com.example.yyw.exception.DefaultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/10/16 15:24
 * @description
 */
@Slf4j
@RequestMapping("/repeatedSubmit")
@RestController
public class RepeatedSubmitController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RepeatSubmit(expireTime = 30, count = 1, message = "请勿重复提交")
    @RequestMapping("/submit1")
    public void methodSubmit1(String id, String type) throws InterruptedException {
        log.info("id: {}, type: {}", id, type);
        Thread.sleep(2000);
    }

    @RequestMapping("/textException")
    public void methodTextException(){
        throw new DefaultException("aaa");
    }
    @RequestMapping("/textException1")
    public void methodTextException1(){
        throw new RuntimeException("aaa");
    }

}
