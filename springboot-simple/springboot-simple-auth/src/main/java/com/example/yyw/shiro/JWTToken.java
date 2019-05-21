package com.example.yyw.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/5 15:16
 * @describe
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
