package com.example.yyw.aop;

import com.example.yyw.annotation.RepeatSubmit;
import com.example.yyw.cache.CacheCounterService;
import com.example.yyw.exception.DefaultException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Title:安全检查切面(是否登录检查)
 * Description: 通过验证Token维持登录状态
 *
 * @author rico
 * @created 2017年7月4日 下午4:32:34
 */
@Component
@Aspect
@Slf4j
public class RepeatSubmitAroundAspect {

    private static final String REPEAT_SUBMIT = "REPEAT_SUBMIT";

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private CacheCounterService cacheCounterService;

    @Around("@annotation(com.example.yyw.annotation.RepeatSubmit)")
    public Object execute(ProceedingJoinPoint joinPoint) {
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

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("error: {}", throwable);
            return null;
        } finally {
            Boolean delete = redisTemplate.delete(key);
            log.info("{}", delete);
        }
    }

    public String getArgsString(Object[] arg) {
        String collect = Arrays.stream(arg).map(o -> String.valueOf(o)).collect(Collectors.joining(","));
        return collect;
    }
}
