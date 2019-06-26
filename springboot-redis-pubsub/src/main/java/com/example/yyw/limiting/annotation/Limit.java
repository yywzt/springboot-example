package com.example.yyw.limiting.annotation;

import java.lang.annotation.*;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/22 15:19
 * @describe
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limit {

    String name() default "";

    String key() default "";

    /**
     * 时间段
     * 单位：秒
     * */
    int expireTime();

    /**
     * 时间段内访问次数上限
     * */
    int count();
}
