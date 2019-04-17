package com.example.yyw.redispubsub.publish;

import com.example.yyw.redispubsub.topic.Message;
import com.example.yyw.redispubsub.topic.TopicEnum;
import com.example.yyw.redispubsub.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/1/10 10:20
 * @desc 发布消息
 */
@RestController
public class TestPub {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/send1")
    public void send1(){
        Message message = new Message(1L,"test1发布消息啦", LocalDateTime.now());
        redisUtil.convertAndSend(TopicEnum.test1.getName(),message);
    }

    @RequestMapping("/send2")
    public void send2(){
        Message message = new Message(1L,"test2发布消息啦", LocalDateTime.now());
        redisUtil.convertAndSend(TopicEnum.test2.getName(),message);
    }

    @RequestMapping("/set1")
    public void set1(){
        redisUtil.set("haha","aaaaa爱的世界");
    }
}
