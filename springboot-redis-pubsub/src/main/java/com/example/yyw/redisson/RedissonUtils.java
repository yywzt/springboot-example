package com.example.yyw.redisson;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/11 19:12
 */
@Slf4j
@Component
public class RedissonUtils {

    @Autowired
    private RedissonClient redissonClient;

    public final int DEFAULT_WAIT_TIME = 60;
    public final int DEFAULT_LEASE_TIME = 30;

    public String getConfig(){
        Config config = redissonClient.getConfig();
        return JSONObject.toJSONString(config);
    }

    public boolean tryLock(String key, int leaseTime, TimeUnit timeUnit) {
        return tryLock(key, DEFAULT_WAIT_TIME, leaseTime, timeUnit);
    }

    public boolean tryLock(String key, int waitTime, int leaseTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitTime, leaseTime, timeUnit);
        } catch (InterruptedException e) {
            log.error("[{}]-->tryLock failure : {}", e.getMessage());
            return false;
        }
    }

    public boolean unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        if(lock.isLocked()){
            lock.unlock();
        }
        return true;
    }
}
