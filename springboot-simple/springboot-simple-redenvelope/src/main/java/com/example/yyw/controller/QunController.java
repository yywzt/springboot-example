package com.example.yyw.controller;

import com.example.yyw.constant.ResponseData;
import com.example.yyw.model.redEnvelope.Qun;
import com.example.yyw.service.QunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzt
 * @date 2019/4/24 23:15
 * @describe
 */
@RestController
@RequestMapping("/qun")
public class QunController {

    @Autowired
    private QunService qunService;

    @PostMapping("/initQun")
    public ResponseData initQun(@Validated Qun qun){
        return qunService.initQun(qun);
    }
}
