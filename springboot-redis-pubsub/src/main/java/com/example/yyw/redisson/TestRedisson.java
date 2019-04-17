package com.example.yyw.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author yanzt
 * @date 2019/4/18 0:02
 * @describe
 */
@RestController
@RequestMapping("/redisson")
public class TestRedisson {

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("/tryLock")
    public String testTryLock(String key,long time){
        String s = "";
        RLock lock = redissonClient.getLock(key);
        try {
            if(lock.tryLock(1,time, TimeUnit.SECONDS)){
                s = "tryLock success---";
            }else{
                s = "tryLock failure---";
            }
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s;
    }
}
