package com.example.yyw.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/10/17 10:46
 * @description
 */
@Slf4j
public class RateLimiterTest {

    @Test
    public void test_1() throws ExecutionException, InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create((double)1/3);
//        RateLimiter rateLimiter = RateLimiter.create(1, 3, TimeUnit.SECONDS);
        int taskSize = 5;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);

        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("1");
                return "hahaha";
            };
            /*if(rateLimiter.tryAcquire()) {
                // 执行任务并获取Future对象
                Future f = pool.submit(c);
                list.add(f);
            }*/
            rateLimiter.acquire(1);
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }
        System.out.println("end");
    }

    @Test
    public void test_2() throws ExecutionException, InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(1);
//        RateLimiter rateLimiter = RateLimiter.create((double)1/3);

        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(rateLimiter.tryAcquire()){
                log.info("SUCCESS!");
            }else {
                log.info("FAILURE!");
            }
        }

    }

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test_3() throws InterruptedException {
        Config config = new Config();
        config.useSingleServer()
                .setDatabase(2)
                .setPassword("root")
                .setTimeout(1000000)
                .setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setDatabase(4);
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setPassword("root");
        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettuceClientConfiguration.builder();
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, builder.build());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();

        RRateLimiter rateLimiter = redisson.getRateLimiter("RATELIMITER:test_3");
        // 初始化
        // 最大流速 = 每1秒钟产生10个令牌
        rateLimiter.trySetRate(RateType.OVERALL, 1, 1, RateIntervalUnit.SECONDS);

        for(int i=0;i<100000;i++){
            if(rateLimiter.tryAcquire()){
                log.info("tryAcquire SUCCESS");
            }else {
                log.warn("tryAcquire FAILURE");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
