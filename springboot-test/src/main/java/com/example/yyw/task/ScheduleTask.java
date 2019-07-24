package com.example.yyw.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/7/23 22:49
 * @describe
 */
@Slf4j
public class ScheduleTask {

    private static ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

    static {
        threadPoolTaskScheduler.setPoolSize(3);
        threadPoolTaskScheduler.initialize();
    }

    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledFuture<?> schedule = threadPoolTaskScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("{}", RandomUtils.nextInt());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error("{}", e.getMessage());
                }
            }
        }, 2000L);

        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                log.info("{}", RandomUtils.nextInt());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.error("{}", e.getMessage());
                }
            }
        }, 1L, 2000L, TimeUnit.MILLISECONDS);

    }
}
