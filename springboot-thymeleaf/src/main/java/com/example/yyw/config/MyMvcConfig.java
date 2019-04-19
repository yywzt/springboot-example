package com.example.yyw.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        registry.addViewController("/form1").setViewName("form1");
        registry.addViewController("/form2").setViewName("form2");
        registry.addViewController("/form3").setViewName("Form3");
        registry.addViewController("/test").setViewName("test");
    }

    /**
     * 配置自定义静态资源路径
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/js/");
    }
}
