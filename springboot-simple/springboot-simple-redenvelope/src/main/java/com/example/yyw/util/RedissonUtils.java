package com.example.yyw.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author yanzt
 * @date 2019/4/24 23:51
 * @describe
 */
@Slf4j
public class RedissonUtils {

    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MILLISECONDS;
    private static final long DEFAULT_TIME_OUT = 5 * 1000;

    private static RedissonUtils utils = new RedissonUtils();

    private RedissonUtils() {

    }

    /**
     * 获取一个RedissonUtils实例
     *
     * @return
     */
    public static RedissonUtils getInstance() {
        return utils;
    }

    /**
     * 获取分布式锁
     *
     * @param key 分布式锁的key值
     * @return 返回分布式锁
     */
    public RLock getRLock(String key) {
        RLock rLock = getRedissonClient().getLock(key);
        return rLock;
    }


    /**
     * 给分布式锁上锁
     *
     * @param key       key
     * @param leaseTime 超时时间
     * @param unit      时间单位
     */
    public void lock(String key, long leaseTime, TimeUnit unit) {
        RLock rlock = getRLock(key);
        rlock.lock(leaseTime, unit);
    }

    /**
     * 给分布式锁上锁，默认时间为5秒
     *
     * @param key key
     */
    public void lock(String key) {
        RLock rlock = getRLock(key);
        rlock.lock(DEFAULT_TIME_OUT, DEFAULT_TIME_UNIT);
    }

    /**
     * 给分布式锁上锁，默认时间为时间单位为毫秒
     *
     * @param key       key
     * @param leaseTime 超时时间
     */
    public void lock(String key, long leaseTime) {
        RLock rlock = getRLock(key);
        rlock.lock(leaseTime, DEFAULT_TIME_UNIT);
    }


    /**
     * 给分布式锁解锁。
     *
     * @param key key
     */
    public void unlock(String key) {
        RLock rlock = getRLock(key);
        rlock.unlock();
    }


    /**
     * 给分布式锁强制解锁
     *
     * @param key key
     */
    public void forceUnlock(String key) {
        RLock rlock = getRLock(key);
        rlock.forceUnlock();
    }

    /**
     * 尝试获取锁，如果获取到，返回true
     *
     * @param key       key
     * @param waitTime  最大等待时间
     * @param leaseTime 剩余时间
     * @param unit
     * @return
     */
    public boolean tryLock(String key, long waitTime, long leaseTime, TimeUnit unit) {
        RLock rLock = getRLock(key);
        try {
            return rLock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            // e.printStackTrace();
            return false;
        }
    }


    /**
     * 尝试获取锁，如果获取到，返回true
     *
     * @param key       key
     * @param waitTime  最大等待时间
     * @param leaseTime 剩余时间
     * @return
     */
    public boolean tryLock(String key, long waitTime, long leaseTime) {
        return this.tryLock(key, waitTime, leaseTime, DEFAULT_TIME_UNIT);
    }

    /**
     * 获取原子性的LONG
     *
     * @param key KEY
     * @return
     */
    public RAtomicLong getAtomicLong(String key) {
        RAtomicLong atomicLong = this.getRedissonClient().getAtomicLong(key);
        return atomicLong;
    }

    /**
     * 获取原子性的LONG
     *
     * @param key KEY
     * @param num 设置的值
     * @return
     */
    public RAtomicLong getAtomicLong(String key, long num) {
        RAtomicLong atomicLong = this.getRedissonClient().getAtomicLong(key);
        atomicLong.set(num);
        return atomicLong;
    }

    /**
     * 获取消息主题
     *
     * @param key KEY
     * @return
     */
    public RTopic getRTopic(String key) {
        RTopic topic = this.getRedissonClient().getTopic(key);
        return topic;
    }


    /**
     * 获取RedissonClient客户端
     *
     * @return
     */
    public RedissonClient getRedissonClient() {
        return SpringUtils.getBean(RedissonClient.class);
    }
}
