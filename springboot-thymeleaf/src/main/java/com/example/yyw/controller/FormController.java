package com.example.yyw.controller;

import com.example.yyw.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yanzt
 * @date 2019/4/19 11:44
 * @describe
 */
@RestController
@RequestMapping("/form")
public class FormController {

    @RequestMapping(value = "/submitForm1",method = RequestMethod.POST)
    public List<User> form1(@RequestBody List<User> userList){
        System.out.println(userList);
        return userList;
    }

    @RequestMapping(value = "/submitForm2",method = RequestMethod.POST)
    public User form1(User user){
        System.out.println(user);
        return user;
    }
}
