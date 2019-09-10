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
    /**等待任务在关机时完成--true表明等待所有线程执行完成再优雅停机*/
    private boolean waitForTasksToCompleteOnShutdown = true;
    /**等待时间 （默认为0，此时立即停止），并等待xx秒后强制停止*/
    private int awaitTerminationSeconds = 30;
    /**线程名称前缀*/
    private String threadNamePrefix = "MyScheduler-";

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(poolSize);
        taskScheduler.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        taskScheduler.setAwaitTerminationSeconds(awaitTerminationSeconds);
        taskScheduler.setThreadNamePrefix(threadNamePrefix);
        taskScheduler.initialize();
        return taskScheduler;
    }
}
