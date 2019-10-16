package com.example.yyw.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class CacheCounterService {
    private RedisTemplate<String, String> redisTemplate;
    private Map<String, RedisAtomicLong> redisAtomicLongMap = new ConcurrentHashMap<>();

    @Autowired
    public CacheCounterService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private RedisAtomicLong getRedisAtomicLong(String cacheKey) {
        if (redisAtomicLongMap.containsKey(cacheKey) && redisTemplate.hasKey(cacheKey)) {
            return redisAtomicLongMap.get(cacheKey);
        }
        return createRedisAtomicLong(cacheKey, null, null);
    }

    private RedisAtomicLong createRedisAtomicLong(String cacheKey, Integer expire, TimeUnit timeUnit) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(cacheKey, redisTemplate.getConnectionFactory());
        redisAtomicLongMap.put(cacheKey, redisAtomicLong);
        if (expire != null) {
            redisAtomicLong.expire(expire, timeUnit);
        }
        return redisAtomicLong;
    }


    private RedisAtomicLong getRedisAtomicLong(String cacheKey, Integer expire, TimeUnit timeUnit) {
        if (redisAtomicLongMap.containsKey(cacheKey) && redisTemplate.hasKey(cacheKey)) {
            return redisAtomicLongMap.get(cacheKey);
        }
        return createRedisAtomicLong(cacheKey, expire, timeUnit);
    }


    public Long add(String cacheKey, long delta) {
        return getRedisAtomicLong(cacheKey).addAndGet(delta);
    }

    public Long add(String cacheKey, long delta, Integer expire, TimeUnit timeUnit) {
        return getRedisAtomicLong(cacheKey, expire, timeUnit).addAndGet(delta);
    }


    public Long get(String cacheKey) {
        return getRedisAtomicLong(cacheKey).get();
    }

    public Long get(String cacheKey, Integer expire, TimeUnit timeUnit) {
        return getRedisAtomicLong(cacheKey, expire, timeUnit).get();
    }

    public void set(String cacheKey, Long newCount) {
        getRedisAtomicLong(cacheKey).set(newCount);
    }

    public void set(String cacheKey, Long newCount, Integer expire, TimeUnit timeUnit) {
        getRedisAtomicLong(cacheKey, expire, timeUnit).set(newCount);
    }

    public void reset(String cacheKey, Integer expire, TimeUnit timeUnit) {
        set(cacheKey, 0L, expire, timeUnit);
    }
}
