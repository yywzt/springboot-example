package com.example.yyw.limiting.aspect;

import com.example.yyw.exception.DefaultException;
import com.example.yyw.limiting.annotation.Limit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/22 15:23
 * @describe
 */
@Component
@Slf4j
@Aspect
public class LimitAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("execution(public * com.example.yyw.limiting..*.*Controller.*(..)) || @annotation(com.example.yyw.limiting.annotation.Limit)")
    public void limit(){

    }

    @Before("limit()")
    public void doBefore(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Class<?> aClass = joinPoint.getTarget().getClass();

        Method method = methodSignature.getMethod();
        if(aClass.isAnnotationPresent(Limit.class) || method.isAnnotationPresent(Limit.class)){
            Limit classAnnotation = aClass.getAnnotation(Limit.class);
            Limit methodAnnotation = method.getAnnotation(Limit.class);
            Limit limit = methodAnnotation == null ?classAnnotation:methodAnnotation;
            if(limit != null){
                //限流处理
                String key = limit.key();
                int expireTime = limit.expireTime();
                int count = limit.count();

                Object test2_ = redisTemplate.opsForValue().get(key);
                RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
                if(test2_ == null) {
                    redisAtomicInteger.expire(expireTime, TimeUnit.SECONDS);
                }
                int addAndGet = redisAtomicInteger.addAndGet(1);
                if(addAndGet > count){
                    throw new DefaultException("单位时间内请求次数过多");
                }
            }
        }
    }
}
