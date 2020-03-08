package com.example.springbootrabbitmq.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2020/2/3 20:58
 * @describe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerSimpleTest {

    @Autowired
    private ProducerSimple producerSimple;

    @Test
    public void send1() {
        String exchange = "exchange";
        String routingKey = "hello1";
        String queue = "hello1";
        String message = "Hello";
        producerSimple.send1(exchange, routingKey,queue, message);
    }
}