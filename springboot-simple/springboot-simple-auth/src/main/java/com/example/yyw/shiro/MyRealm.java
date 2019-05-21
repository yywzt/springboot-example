package com.example.yyw.shiro;

import com.example.yyw.exception.DefaultException;
import com.example.yyw.model.ssm.UserInf;
import com.example.yyw.service.UserInfService;
import com.example.yyw.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/5 15:20
 * @describe
 */
@Service
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserInfService userInfService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUserName(principals.toString());
        UserInf userInf = userInfService.getUserInf(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(userInf.getRoles());
        simpleAuthorizationInfo.addStringPermissions(userInf.getPermission());
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUserName(token);
        if (username == null) {
            throw new DefaultException("token invalid");
        }

        UserInf userInf = userInfService.getUserInf(username);
        if (userInf == null) {
            throw new DefaultException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, userInf.getPasswd())) {
            throw new DefaultException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

}
