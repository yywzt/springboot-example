package com.example.yyw.controller;

import com.example.yyw.constant.ResponseData;
import com.example.yyw.model.redEnvelope.User;
import com.example.yyw.service.redEnvelope.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzt
 * @date 2019/4/24 23:10
 * @describe
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/initUser")
    public ResponseData initUser(@Validated User user){
        return userService.initUser(user);
    }
}
