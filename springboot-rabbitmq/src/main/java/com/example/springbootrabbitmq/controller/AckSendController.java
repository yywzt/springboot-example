package com.example.springbootrabbitmq.controller;

import com.example.springbootrabbitmq.config.RabbitMqConfig;
import com.example.springbootrabbitmq.service.AckSendService;
import com.example.yyw.constant.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/11 17:06
 * @Description
 */
@RestController
@RequestMapping("/ack/send")
public class AckSendController {

    @Autowired
    private AckSendService ackSendService;

    @RequestMapping("/convertAndSend")
    public ResponseData convertAndSend(@RequestParam(defaultValue = RabbitMqConfig.DEFAULT_EXCHANGE) String exchange,
                              @RequestParam(defaultValue = RabbitMqConfig.DEFAULT_ROUTING_KEY) String routingKey,
                              @RequestParam String message) {
        ackSendService.convertAndSend(exchange, routingKey, message);
        return ResponseData.success();
    }
}
