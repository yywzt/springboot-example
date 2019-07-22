package com.example.yyw.springbootkafka.controller;

import com.example.yyw.springbootkafka.service.ProduceService;
import com.example.yyw.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/9 19:23
 * @describe
 */
@RestController
public class ProduceController {

    @Autowired
    private ProduceService produceService;

    @RequestMapping("/send_one")
    public Object send_one(String topic) {
        produceService.produce_one(topic);
        return ResultUtil.successResult();
    }

    @RequestMapping("/send_two")
    public Object send_two(String topic) {
        produceService.produce_two(topic);
        return ResultUtil.successResult();
    }
}
