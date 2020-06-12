package com.example.springbootrabbitmq.producer;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2020/2/3 20:54
 * @describe 生产者示例
 */
@Service
public class ProducerSimple {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    public void send1(String exchange, String routingKey, String queue, String message) {
        DirectExchange ex = new DirectExchange(exchange);
        Queue q = new Queue(queue);
        amqpAdmin.declareExchange(ex);
        amqpAdmin.declareQueue(q);
        amqpAdmin.declareBinding(BindingBuilder.bind(q).to(ex).with(routingKey));
        //设置确认机制
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                if(!ack) {
//                    logger.info("send message failed: \" + cause");
//                    throw new ApplicationException(MqCenterCode.FAILED);
//                }
//            }
//        });
        //发送消息
        rabbitTemplate.convertAndSend(exchange, routingKey, message, new CorrelationData(UUID.randomUUID().toString()));
    }

}
