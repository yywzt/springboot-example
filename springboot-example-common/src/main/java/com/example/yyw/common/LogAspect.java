package com.example.yyw.common;

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
import java.util.Enumeration;
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
//        System.out.println("后置通知");
    }

    public void loginfo(JoinPoint joinPoint,String returnValue){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        StringBuilder sb = new StringBuilder("\nSpringBoot action report -------------")
                .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()))
                .append(" ------------------------------\n");
        // 记录下请求内容
        String remoteAddr = request.getRemoteAddr();
        sb.append("RemoteAddr : ").append(remoteAddr).append("\n");
        String URL = request.getRequestURL().toString();
        sb.append("URL : ").append(URL).append("\n");
        String HTTP_METHOD = request.getMethod();
        sb.append("HTTP_METHOD : ").append(HTTP_METHOD).append("\n");
        // 如果是表单，参数值是普通键值对。如果是application/json，则request.getParameter是取不到的。
        String HTTP_HEAD_Type = request.getHeader("Content-Type");
        sb.append("HTTP_HEAD_Type : ").append(HTTP_HEAD_Type).append("\n");
        String controller = joinPoint.getTarget().getClass().getName();
        sb.append("Controller : ").append(controller).append("\n");
        String CLASS_METHOD = joinPoint.getSignature().getName();
        sb.append("CLASS_METHOD : ").append(CLASS_METHOD).append("\n");

        sb.append("Parameter   : " + buildParameterString(request) + "\n");
        sb.append("return value: ").append(returnValue).append("\n");
        long times = System.currentTimeMillis() - startTime.get();
        sb.append("times(ms)   : ").append((times)).append("ms").append("\n");
        sb.append("--------------------------------------------------------------------------------\n");
        log.info(sb.toString());
    }

    private String buildParameterString(HttpServletRequest request) {
        if ("application/json".equals(request.getHeader("Content-Type"))) {
            // 记录application/json时的传参，SpringMVC中使用@RequestBody接收的值
            return getRequestPayload(request);
        } else {
            //记录请求的键值对
            if (null == request) {
                return "";
            }
            Enumeration<String> e = request.getParameterNames();
            StringBuilder parameterStr = new StringBuilder();
            while (e.hasMoreElements()) {
                String key = e.nextElement();
                String[] values = request.getParameterValues(key);
                if (values.length == 1) {
                    parameterStr.append(key).append("=").append(values[0]);
                } else {
                    parameterStr.append(key).append("[]={");
                    for (int i = 0; i < values.length; i++) {
                        if (i > 0) {
                            parameterStr.append(",");
                        }
                        parameterStr.append(values[i]);
                    }
                    parameterStr.append("}");
                }
                parameterStr.append("  ");
            }
            return parameterStr.toString();
            /*for (String key : request.getParameterMap().keySet()) {
                log.info(key + "----" + request.getParameter(key));
            }*/
        }
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
