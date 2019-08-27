package com.example.yyw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/27 16:42
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {

    /**当前线程池数*/
    private int poolSize = 10;
    /**线程名称前缀*/
    private String threadNamePrefix = "MyScheduler-";

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(poolSize);
        taskScheduler.setThreadNamePrefix(threadNamePrefix);
        taskScheduler.initialize();
        return taskScheduler;
    }
}
