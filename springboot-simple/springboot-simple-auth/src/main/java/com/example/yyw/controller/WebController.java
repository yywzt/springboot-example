package com.example.yyw.controller;

import com.example.yyw.exception.DefaultException;
import com.example.yyw.model.ssm.UserInf;
import com.example.yyw.service.UserInfService;
import com.example.yyw.util.EncodeUtil;
import com.example.yyw.util.JWTUtil;
import com.example.yyw.config.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/29 22:54
 * @describe
 */
@RestController
@RequestMapping("/web")
public class WebController {
    
    @Autowired
    private UserInfService userInfService;

    @PostMapping("/signToken")
    public ResponseData login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        UserInf userInf = userInfService.getUserInf(username);
        if (userInf.getPasswd().equals(EncodeUtil.md5(password))) {
            return ResponseData.success("Login success", JWTUtil.sign(userInf.getUname(), userInf.getPasswd()));
        } else {
            throw new DefaultException("用户名密码错误");
        }
    }

    @GetMapping("/article")
    public ResponseData article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResponseData.success("You are already logged in");
        } else {
            return ResponseData.success("You are guest");
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseData requireAuth() {
        return ResponseData.success("You are authenticated");
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseData requireRole() {
        return ResponseData.success("You are visiting require_role");
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseData requirePermission() {
        return ResponseData.success("You are visiting permission require edit,view");
    }

    @RequestMapping(value = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseData unauthorized() {
        return ResponseData.failure("401", "Unauthorized");
    }

}
