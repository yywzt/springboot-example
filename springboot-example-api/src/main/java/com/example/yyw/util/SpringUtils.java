package com.example.yyw.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/7 16:25
 * @describe
 */
@Configuration
@Slf4j
public class SpringUtils implements ApplicationContextAware {

    public static String applicationName = null;
    public static String applicationAbbr = null;

    private static ApplicationContext ctx = null;

    public static Object getBean(String beanName) {
        if (ctx != null) {
            try {
                return ctx.getBean(beanName);
            } catch (Exception e) {
                log.error("获取Bean失败！", e);
                return null;
            }
        }

        return null;
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        T result = null;
        if (ctx != null) {
            try {
                result = ctx.getBean(beanName, requiredType);
            } catch (Exception e) {
                log.error("获取Bean失败！", e);
            }
        }

        return result;
    }

    public static <T> T getBean(Class<T> requiredType) {
        T result = null;
        if (ctx != null) {
            try {
                result = ctx.getBean(requiredType);
            } catch (Exception e) {
                log.error("获取Bean失败！", e);
            }
        }

        return result;
    }

    public static void publishEvent(ApplicationEvent event) {
        if (ctx != null) {
            ctx.publishEvent(event);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationName = applicationContext.getId();
        ctx = applicationContext;
        applicationAbbr = getProperty("spring.application.abbr", applicationName);
    }

    public static String getProperty(String key, String defaultValue) {
        String result = defaultValue;
        Environment environment = getBean(Environment.class);
        if (environment != null) {
            result = environment.getProperty(key, defaultValue);
        }

        return result;
    }
}
