package com.example.springbootjedis.yyw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;

@Component
public class RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    public void set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        }
    }

    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                return jedis.get(key);
            }
            return null;
        }
    }

    public void hset(String key, Map<String, String> map) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hmset(key, map);
        }
    }

    public void hset(String key, String field, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(key, field, value);
        }
    }

    public Map<String, String> hget(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                return jedis.hgetAll(key);
            }
            return null;
        }
    }

    public String hget(String key, String field) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.hexists(key, field)) {
                return jedis.hget(key, field);
            }
            return null;
        }
    }

    public void expire(String key, int seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.expire(key, seconds);
        }
    }

    public void expireAt(String key, Date expireDate) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.expireAt(key, expireDate.getTime()/1000);
        }
    }

    /**
     * @param key
     */
    public Set<String> smembers(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.smembers(key);
        }
    }

    /**
     *  将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     *  假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
     *  当 key 不是集合类型时，返回一个错误。
     * @param key
     * @param value
     */
    public void sadd(String key, String... value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.sadd(key,value);
        }
    }

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     * 当 key 不是集合类型，返回一个错误。
     * @param key
     * @param value
     */
    public void srem(String key, String... value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.srem(key,value);
        }
    }

    /**
     * 当 oldKey 和 newKey 相同，或者 oldKey 不存在时，返回一个错误。
     * 当 newKey 已经存在时， rename 命令将覆盖旧值。
     * @param oldKey
     * @param newKey
     */
    public void rename(String oldKey, String newKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.rename(oldKey, newKey);
        }
    }
    /**
     * 删除指定的键。如果给定的key不存在，
     * 则不对该key执行任何操作。
     * 该命令返回删除的键数。时间复杂度：O（1）
     * @param key
     */
    public Long del(String... key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(key);
        }
    }

    /**
     * 查找所有符合给定模式 pattern 的 key
     * @param pattern
     * @return 返回符合条件的key集合
     */
    public Set<String> keys(String pattern) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys(pattern + "*");
        }
    }

    public List<String> scans(String pattern, Integer count) {
        try (Jedis jedis = jedisPool.getResource()) {
            List<String> list = new ArrayList<>();
            String cursor = "0";
            while(true) {
                ScanParams params = new ScanParams();
                params.match(pattern);
                params.count(count);
                ScanResult<String> scanResult = jedis.scan(cursor, params);
                List<String> elements = scanResult.getResult();
                if (elements != null && elements.size() > 0) {
                    list.addAll(elements);
                }
                cursor = scanResult.getStringCursor();
                if ("0".equals(cursor)) {
                    break;
                }
            }
            return list;
        }
    }
}
