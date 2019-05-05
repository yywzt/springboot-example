package com.example.yyw.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/5 14:15
 * @describe
 */
@Slf4j
public class JWTUtil {

    public static void main(String[] args){
        String userName = "aaa";
        String secret = "e10adc3949ba59abbe56e057f20f883e";
        String token = sign(userName, secret);
        boolean verify = verify(token, userName, secret);
        log.info("verify: {}",verify);
        verify = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTcwNTAxNzIsInVzZXJuYW1lIjoiYWFhIn0.h2Kz5aeCtRed6dZZNJokNaYyE0scYCNeHfVLpdEJ73I", "aaa", "e10adc3949ba59abbe56e057f20f883e");
        log.info("verify1: {}",verify);
    }

    // 过期时间1小时
    private static final long EXPIRE_TIME = 1*60*60*1000;

    private static final String USERNAME = "username";

    /**
     * Create and Sign a Token
     * @param userName 用户名
     * @param secret 密码
     */
    public static String sign(String userName,String secret){
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withClaim(USERNAME,userName)
                    .withExpiresAt(date)
                    .sign(algorithm);
            log.info("sign token: {}",token);
            return token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            log.error("sign token failure: {}",exception.getMessage());
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUserName(String token){
        DecodedJWT decode = null;
        try {
            decode = JWT.decode(token);
            return decode.getClaim(USERNAME).asString();
        } catch (JWTDecodeException e) {
            log.error("decode failure : {}",e.getMessage());
            return null;
        }
    }

    /**
     * @param token token
     * @param userName 用户名
     * @param secret 密码
     */
    public static boolean verify(String token,String userName,String secret){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(USERNAME,userName)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            log.info("verify success: {}",jwt);
            return true;
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            log.error("verify failure : {}",exception.getMessage());
            return false;
        }
    }
}
