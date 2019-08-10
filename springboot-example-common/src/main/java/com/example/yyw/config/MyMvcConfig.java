package com.example.yyw.config;


import com.example.yyw.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @author yw
 * @create 2018-06-20 16:17
 * @description: 扩展MVC功能
 */
//添加该注解  则全面接管springMVC  所有的SpringMVC的自动配置都失效
//@EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 等价于<mvc:view-controller path="/success" view-name="success"/>
//        registry.addViewController("/loging").setViewName("login-1");
    }

    /**
     * 跨域处理
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "null", "*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .allowedHeaders("Content-Type", "X-Token")
                .maxAge(3600L);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor()).addPathPatterns("/**");
        //日志拦截器
//        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
        //登录拦截器
//        registry.addInterceptor(logingInterceptor()).addPathPatterns("/**").excludePathPatterns("/loging","/login","/doLoging","/Redis/**");
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("authFilter");
        registration.setOrder(1);
        return registration;
    }

}
