package com.example.yyw.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/27 16:07
 * 多线程执行定时任务
 * 在定时任务的类或方法添加注解@Async
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    /**
     * 当前线程池数
     */
    private int corePoolSize = 10;
    /**
     * 最大线程池数
     */
    private int maxPoolSize = 10;
    /**
     * 线程池所使用的缓冲队列
     */
    private int queueCapacity = 10;
    /**
     * 等待任务在关机时完成--true表明等待所有线程执行完成再优雅停机
     */
    private boolean waitForTasksToCompleteOnShutdown = true;
    /**
     * 等待时间 （默认为0，此时立即停止），并等待xx秒后强制停止
     */
    private int awaitTerminationSeconds = 30;
    /**
     * 线程名称前缀
     */
    private String threadNamePrefix = "MyAsync-";

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }

    /**
     * 自定义异常处理类
     *
     * @author yanzt
     */
    class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        //手动处理捕获的异常
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
//            StringBuffer stringBuffer = new StringBuffer("-------------》》》捕获线程异常信息");
//            stringBuffer.append("Exception message - " + throwable.getMessage());
//            stringBuffer.append("Method name - " + method.getName());
//            for (Object param : obj) {
//                stringBuffer.append("Parameter value - " + param);
//            }
//            log.error(stringBuffer.toString());
            log.error("Class: {}, Method: {}, Exception: {}", method.getDeclaringClass().getName(), method.getName(), throwable);
        }

    }
}
