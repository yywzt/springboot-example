package com.example.yyw.util;

import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/23 11:27
 * @describe
 */
public class CodecUtil {

    private static final String salt = "yanzhitao.com";

    public static String createUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * MD5加密
     * */
    public static String md5(String pwd){
        return DigestUtils.md5DigestAsHex(pwd.getBytes());
    }

    /**
     * BCrypt强哈希方法，每次加密的结果都不一样。
     * */
//    public static String BCrypt(String pwd){
//        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
//        return cryptPasswordEncoder.encode(pwd);
//    }

    public static String md5Encode(String pwd){
        pwd = pwd + salt;
        return DigestUtils.md5DigestAsHex(pwd.getBytes());
    }

}
