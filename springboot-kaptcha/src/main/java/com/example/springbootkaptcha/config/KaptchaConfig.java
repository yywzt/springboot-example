package com.example.springbootkaptcha.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/23 11:25
 * @describe
 */
@Configuration
public class KaptchaConfig {

    @Bean("defaultKaptcha")
    public DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties=new Properties();
        //是否有边框 默认为true 我们可以自己设置yes，no
        properties.setProperty(Constants.KAPTCHA_BORDER, "no");
        //边框颜色 默认为Color.BLACK
//        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "105,179,90");
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "LIGHT_GRAY");
        //边框粗细度 默认为1
        properties.setProperty(Constants.KAPTCHA_BORDER_THICKNESS, "1");;
        // 验证码噪点颜色 默认为Color.BLACK
        properties.setProperty(Constants.KAPTCHA_NOISE_COLOR, "BLACK");
        //验证码噪点生成对象 默认为DefaultNoise
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL, "com.google.code.kaptcha.impl.DefaultNoise");
        //验证码样式引擎 默认为WaterRipple
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        //验证码生成器 默认为DefaultKaptcha
        properties.setProperty(Constants.KAPTCHA_PRODUCER_IMPL, "com.google.code.kaptcha.impl.DefaultKaptcha");
        //验证码文本生成器 默认为DefaultTextCreator
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_IMPL, "com.google.code.kaptcha.text.impl.DefaultTextCreator");
        // 验证码文本字符内容范围 默认为abcde2345678gfynmnpwx
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        // 验证码文本字符长度 默认为5
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
        // 验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
//        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "125");
        //验证码文本字符颜色 默认为Color.BLACK
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        //验证码文本字符大小 默认为40
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "22");
        //验证码文本字符间距 默认为2
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "6");
        //验证码文本字符渲染 默认为DefaultWordRenderer
        properties.setProperty(Constants.KAPTCHA_WORDRENDERER_IMPL, "com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        //验证码背景生成器 默认为DefaultBackground
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_IMPL, "com.google.code.kaptcha.impl.DefaultBackground");
        //验证码背景颜色渐进 默认为Color.LIGHT_GRAY
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "WHITE");
        //验证码背景颜色渐进 默认为Color.WHITE
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_TO, "WHITE");
        //验证码图片宽度 默认为200
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "125");
        //验证码图片高度 默认为50
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "45");

        Config config=new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;

    }
}
