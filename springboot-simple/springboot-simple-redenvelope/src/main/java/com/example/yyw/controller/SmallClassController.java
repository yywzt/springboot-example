package com.example.yyw.controller;

import com.example.yyw.service.smallClass.ManorSmallClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/29 11:56
 * @describe
 */
@RestController
@RequestMapping("/question")
public class SmallClassController {

    @Autowired
    private ManorSmallClassService manorSmallClassService;

    @RequestMapping("/getQuestion")
    public Object getQuestion(){
        return manorSmallClassService.getManorSmallClass();
    }
}
