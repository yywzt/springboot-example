package com.example.yyw.redisson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author yanzt
 * @date 2019/4/18 0:02
 * @describe
 */
@Slf4j
@RestController
@RequestMapping("/redisson")
public class TestRedisson {

    @Autowired
    private RedissonUtils redissonUtils;

    @RequestMapping("/tryLock")
    public String testTryLock(String key, int time) {
        String s = "";
        try {
            if (redissonUtils.tryLock(key, time, TimeUnit.SECONDS)) {
                s = "tryLock success---";
            } else {
                s = "tryLock failure---";
            }
            log.info(s);
        } finally {
            redissonUtils.unlock(key);
        }
        return s;
    }

    @RequestMapping("/getConfig")
    public String getConfig(){
        return redissonUtils.getConfig();
    }

}
