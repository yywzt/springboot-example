package com.example.springbootrabbitmq.service;

import com.example.springbootrabbitmq.util.RabbitMqUtil;
import com.example.yyw.constant.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/11 17:07
 * @Description
 */
@Service
public class AckSendService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitMqUtil rabbitMqUtil;

    public ResponseData convertAndSend(String exchange, String routingKey, String message) {
        rabbitMqUtil.convertAndSend(exchange, routingKey, message);
        return ResponseData.success();
    }
}
