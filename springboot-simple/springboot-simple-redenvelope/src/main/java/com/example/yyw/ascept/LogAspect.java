package com.example.yyw.ascept;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author ywyw2424@foxmail.com
 * @date 2018/9/28 9:30
 * @desc controller日子切面
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    ThreadLocal<String> timeTag = new ThreadLocal<String>();

    @Pointcut("execution(public * com.example..*.*Controller.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());  //记录启动前时间
        timeTag.set(UUID.randomUUID() + "");
    }

    @AfterReturning(returning = "ret",pointcut = "log()")
    public void doReturn(JoinPoint joinPoint,Object ret){
        loginfo(joinPoint,ret.toString());
    }

    @AfterThrowing(throwing = "ex",pointcut = "log()")
    public void afterThrowing(JoinPoint joinPoint,Exception ex){
        loginfo(joinPoint,ex.getMessage());
    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("后置通知");
    }

    public void loginfo(JoinPoint joinPoint,String returnValue){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        StringBuilder sb = new StringBuilder("\nSpringBoot action report -------------")
                .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .append(" ------------------------------\n");
        // 记录下请求内容
        String remoteAddr = request.getRemoteAddr();
        sb.append("RemoteAddr : ").append(remoteAddr).append("\n");
        String controller = joinPoint.getTarget().getClass().getName();
        sb.append("Controller : ").append(controller).append("\n");


        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        // 如果是表单，参数值是普通键值对。如果是application/json，则request.getParameter是取不到的。
        log.info("HTTP_HEAD Type : " + request.getHeader("Content-Type"));
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        if ("application/json".equals(request.getHeader("Content-Type"))) {
            // 记录application/json时的传参，SpringMVC中使用@RequestBody接收的值
            log.info(getRequestPayload(request));
        } else {
            //记录请求的键值对
            for (String key : request.getParameterMap().keySet()) {
                log.info(key + "----" + request.getParameter(key));
            }
        }
        long times = System.currentTimeMillis() - startTime.get();
        sb.append("times(ms)   : ").append((times)).append("ms").append("\n");
        sb.append("--------------------------------------------------------------------------------\n");

        System.out.println("前置通知");
    }

    private String getRequestPayload(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
