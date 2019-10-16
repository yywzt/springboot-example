package com.example.yyw.aop;

import com.example.yyw.annotation.RepeatSubmit;
import com.example.yyw.cache.CacheCounterService;
import com.example.yyw.exception.DefaultException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/10/16 16:21
 * @description
 */
@Slf4j
@Aspect
//@Component
public class RepeatSubmitAspect {

    private static final String REPEAT_SUBMIT = "REPEAT_SUBMIT";

    ThreadLocal<String> keys = new ThreadLocal<String>();

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private CacheCounterService cacheCounterService;

    @Pointcut("@annotation(com.example.yyw.annotation.RepeatSubmit)")
    public void repeatSubmit() {

    }

    @Before("repeatSubmit()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Class<?> aClass = joinPoint.getTarget().getClass();
        Method method = methodSignature.getMethod();
        RepeatSubmit repeatSubmit = method.getAnnotation(RepeatSubmit.class);
        //限流处理
        String key = REPEAT_SUBMIT + ":" + aClass.getSimpleName() + "_" + method.getName() + ":" + getArgsString(joinPoint.getArgs());
        int expireTime = repeatSubmit.expireTime();
        TimeUnit timeUnit = repeatSubmit.timeUnit();
        long max_count = repeatSubmit.count();
        String message = repeatSubmit.message();

        Long count = cacheCounterService.add(key, 1, expireTime, timeUnit);
        if (count > max_count) {
            throw new DefaultException(StringUtils.isBlank(message) ? "单位时间内请求次数过多" : message);
        }
    }

    @AfterReturning(returning = "returnValue", pointcut = "repeatSubmit()")
    public void afterReturn(JoinPoint joinPoint, Object returnValue) {
        redisTemplate.delete(keys.get());
    }

    public String getArgsString(Object[] arg) {
        String collect = Arrays.stream(arg).map(o -> String.valueOf(o)).collect(Collectors.joining(","));
        return collect;
    }

}
