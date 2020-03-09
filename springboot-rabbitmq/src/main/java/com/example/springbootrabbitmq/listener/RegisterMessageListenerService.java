package com.example.springbootrabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2020/3/9 22:59
 * @describe
 */
@Slf4j
@Service
public class RegisterMessageListenerService {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    public void createListener(String queueName){
        SimpleRabbitListenerEndpoint endpoint = new SimpleRabbitListenerEndpoint();
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        rabbitAdmin.declareQueue(new Queue(queueName));
        endpoint.setQueueNames(queueName);
        endpoint.setAdmin(rabbitAdmin);
        endpoint.setMessageListener(message -> {
            log.info("message: {}", message);
        });

        endpoint.setupListenerContainer(container);
        container.start();
    }

}
