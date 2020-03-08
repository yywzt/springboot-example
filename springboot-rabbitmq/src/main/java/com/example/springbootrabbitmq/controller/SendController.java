package com.example.springbootrabbitmq.controller;

import com.example.springbootrabbitmq.producer.ProducerSimple;
import com.example.yyw.constant.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2020/2/3 21:27
 * @describe
 */
@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private ProducerSimple producerSimple;

    @RequestMapping("/send1")
    public ResponseData send1(@RequestParam(defaultValue = "exchange") String exchange,
                        @RequestParam(defaultValue = "hello1") String routingKey,
                        @RequestParam(defaultValue = "hello1") String queue,
                        @RequestParam String message){
        producerSimple.send1(exchange, routingKey, queue, message);
        return ResponseData.success();
    }
}
