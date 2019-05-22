package com.example.yyw.limiting.controller;

import com.example.yyw.limiting.annotation.Limit;
import com.example.yyw.util.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/22 15:09
 * @describe
 */
@RestController
@RequestMapping("/limit")
@Limit(key = "limit",expireTime = 10,count = 5)
public class LimitController {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    @RequestMapping("/test1")
    public Object limit1(){
        return ResultUtil.successResult(ATOMIC_INTEGER.incrementAndGet());
    }
}

