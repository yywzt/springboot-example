package com.example.springbootkaptcha.controller;

import com.example.yyw.util.CodecUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/23 11:26
 * @describe
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/imgBase64")
    public Object getKaptchaImageBase64(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        String capText = defaultKaptcha.createText();
        System.out.println("capText: " + capText);

        String uuid = CodecUtil.createUUID();
        stringRedisTemplate.opsForValue().set(uuid, capText, 300, TimeUnit.SECONDS);

        BufferedImage bi = defaultKaptcha.createImage(capText);
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", outputStream);
            Base64.Encoder encoder = Base64.getEncoder();
            String captchaBase64 = encoder.encodeToString(outputStream.toByteArray());
            return new HashMap<String,Object>(){{
                put("kaptchaKey",uuid);
                put("kaptchaCode",captchaBase64);
            }};
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value = "/img")
    public ModelAndView getKaptchaImage(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = defaultKaptcha.createText();
        System.out.println("capText: " + capText);

        try {
            String uuid = CodecUtil.createUUID();
            stringRedisTemplate.opsForValue().set(uuid, capText, 300, TimeUnit.SECONDS);
            Cookie cookie = new Cookie("captchaCode", uuid);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage bi = defaultKaptcha.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    @RequestMapping("/del")
    public Boolean del(String key){
        return stringRedisTemplate.delete(key);
    }

}
