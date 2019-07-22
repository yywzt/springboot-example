package com.example.yyw.springbootkafka.service;

import com.alibaba.fastjson.JSONObject;
import com.example.yyw.springbootkafka.message.Message;
import com.example.yyw.springbootkafka.topic.TopicEnum;
import com.example.yyw.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/9 19:16
 * @describe
 */
@Slf4j
@Service
public class ProduceService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void produce_one(String topic) {
        Message message = new Message();
        message.setId(1L);
        message.setMsg("test message1");
        message.setSengTime(TimeUtil.now());
        String jsonMessage = JSONObject.toJSONString(message);
        ListenableFuture future = kafkaTemplate.send(TopicEnum.findByName(topic).getName(), jsonMessage);
        future.addCallback(success -> log.info("消息发送成功: {}", jsonMessage), fail -> log.info("消息发送失败: {}", jsonMessage));
    }

    public void produce_two(String topic) {
        for (int i = 1; i <= 600; i++) {
            Message message = new Message();
            message.setId(Long.valueOf(i));
            message.setMsg("test message" + i);
            message.setSengTime(TimeUtil.now());
            String jsonMessage = JSONObject.toJSONString(message);
            ListenableFuture future = kafkaTemplate.send(TopicEnum.findByName(topic).getName(), jsonMessage);
            future.addCallback(success -> log.info("消息发送成功: {}", jsonMessage), fail -> log.info("消息发送失败: {}", jsonMessage));
        }
    }
}
