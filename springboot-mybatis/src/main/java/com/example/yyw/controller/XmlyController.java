package com.example.yyw.controller;

import com.example.yyw.service.ssm.LogService;
import com.example.yyw.service.xmly.XmlyService;
import com.example.yyw.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/20 15:28
 * @describe
 */
@RestController
@RequestMapping("/xmly")
public class XmlyController {

    @Autowired
    private XmlyService xmlyService;

    @Autowired
    private LogService logService;

    @RequestMapping("/save")
    public Object saveXmlyCategory(){
        return ResultUtil.successResult(xmlyService.saveXmlyCategory());
    }

    @RequestMapping("/alterTable")
    public Object alterTable(){
        xmlyService.alterTable();
        return ResultUtil.successResult();
    }

}
