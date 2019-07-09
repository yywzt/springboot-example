package com.example.yyw.springbootkafka.consume;

import com.alibaba.fastjson.JSONObject;
import com.example.yyw.springbootkafka.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/9 19:15
 * @describe
 */
@Component
@Slf4j
public class ConsumeSimple {

    @KafkaListener(topics = {"test"})
    public void consumeOne(ConsumerRecord<?, ?> consumerRecord) {
        Optional<?> value = Optional.ofNullable(consumerRecord.value());
        if (!value.isPresent()) {
            log.warn("消息不存在");
            return;
        }
        Object o = value.get();
        Message message = JSONObject.parseObject(String.valueOf(o), Message.class);
        log.info("message: {}", message);
    }
}
