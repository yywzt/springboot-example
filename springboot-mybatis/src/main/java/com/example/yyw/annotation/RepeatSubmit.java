package com.example.yyw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/10/16 15:29
 * @description
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {

    String name() default "";

    /**
     * 描述
     * @return
     */
    String descrition() default "";

    /**
     * 时间段
     * 单位：秒
     */
    int expireTime();

    /**
     * 默认TimeUnit.SECONDS
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;


    /**
     * 次数限制
     * @return
     */
    long count();

    /**
     * 自定义错误信息
     * @return
     */
    String message() default "";

}
