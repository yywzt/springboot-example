package com.example.yyw.springbootkafka.consume;

import com.alibaba.fastjson.JSONObject;
import com.example.yyw.springbootkafka.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/9 19:15
 * @describe
 */
@Component
@Slf4j
public class ConsumeSimple2 {

    @KafkaListener(topics = {"test"}, containerFactory = "batchFactory")
    public void batchConsume(List<ConsumerRecord<String, String>> consumerRecords) {
        consumerRecords.stream().forEach(stringStringConsumerRecord -> {
            Optional<?> value = Optional.ofNullable(stringStringConsumerRecord.value());
            if (!value.isPresent()) {
                log.warn("消息不存在");
                return;
            }
            Object o = value.get();
            Message message = JSONObject.parseObject(String.valueOf(o), Message.class);
            log.info("message: {}", message);
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("thread sleep error: {}", e.getMessage());
        }
        log.info("test - 消息消费成功!");
    }
}
