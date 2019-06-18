package com.example.yyw.limiting.controller;

import com.example.yyw.limiting.annotation.Limit;
import com.example.yyw.util.DateUtil;
import com.example.yyw.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/22 15:09
 * @describe
 */
@RestController
@RequestMapping("/limit")
@Limit(key = "limit",expireTime = 10,count = 5)
@Slf4j
public class LimitController {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    @RequestMapping("/test1")
    public Object limit1(){
        return ResultUtil.successResult(ATOMIC_INTEGER.incrementAndGet());
    }

    @RequestMapping("/test2")
    public Object test2(int count){
        Object test2_ = redisTemplate.opsForValue().get("TEST2_");
        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger("TEST2_", redisTemplate.getConnectionFactory());
        if(test2_ == null) {
            Date lastHourOfDay = DateUtil.getLastHourOfDay(new Date());
            redisAtomicInteger.expire(lastHourOfDay.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
        int getAndAdd = redisAtomicInteger.addAndGet(1);
        if( getAndAdd <= count) {
            log.info("有效，do something");
            return getAndAdd;
        }
        return "无效";
    }
}

