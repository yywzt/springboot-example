package com.example.springbootrabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/11 16:32
 * @Description
 */
@Configuration
public class RabbitMqConfig {

    public static final String DEFAULT_EXCHANGE = "direct_change_1";
    public static final String DEFAULT_QUEUE_NAME = "queue_1";
    public static final String DEFAULT_ROUTING_KEY = "routing_key_1";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DEFAULT_EXCHANGE);
    }

    @Bean
    public Queue queue(){
        return new Queue(DEFAULT_QUEUE_NAME);
    }

    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(DEFAULT_ROUTING_KEY);
    }
}
