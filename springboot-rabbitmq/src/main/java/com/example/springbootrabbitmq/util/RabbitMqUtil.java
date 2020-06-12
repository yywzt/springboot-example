package com.example.springbootrabbitmq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/11 16:41
 * @Description
 */
@Component
public class RabbitMqUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //自动注入RabbitTemplate模板类
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        //确认机制
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * correlationData: 回调的相关数据，包含了消息ID
             * ack: ack结果，true代表ack，false代表nack
             * cause: 如果为nack，返回原因，否则为null
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                logger.info("ConfirmCallback: [correlationData: {}, ack: {}, cause: {}]", correlationData, ack, cause);
                if (!ack) {
                    //做一些补偿机制等
                    logger.error("异常处理....");
                }
            }
        });
        //返回机制
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
                                        String exchange, String routingKey) {
                logger.info("ReturnCallback: [return exchange: {}, routingKey: {}, replyCode: {}, replyText: {}]", exchange
                        , routingKey, replyCode, replyText);
            }
        });
        this.rabbitTemplate = rabbitTemplate;
    }

    public void convertAndSend(String exchange, String routingKey, String message) {
        Map<String, Object> headProperties = new HashMap<>();
        headProperties.put("send_time", LocalDateTime.now());
        convertAndSend(exchange, routingKey, message, headProperties);
    }

    public void convertAndSend(String exchange, String routingKey, String message, Map<String, Object> headProperties) {
        MessageHeaders messageHeaders = new MessageHeaders(headProperties);
        //注意导包
        Message msg = MessageBuilder.createMessage(message, messageHeaders);
        //id + 时间戳 ，保证全局唯一 ，这个是实际消息的ID
        //在做补偿性机制的时候通过ID来获取到这条消息进行重发
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //exchange, routingKey, object, correlationData
        rabbitTemplate.convertAndSend(exchange, routingKey, msg, correlationData);
    }

}