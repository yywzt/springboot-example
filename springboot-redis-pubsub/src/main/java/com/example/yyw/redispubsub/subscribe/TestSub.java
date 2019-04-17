package com.example.yyw.redispubsub.subscribe;

import com.example.yyw.redispubsub.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/1/10 10:26
 * @desc
 */
@Component
public class TestSub implements MessageListener {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(message);
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        System.out.println(body);
        System.out.println(channel);
        String msg = (String) redisUtil.getRedisTemplate().getStringSerializer().deserialize(body);
        String topic = (String) redisUtil.getRedisTemplate().getStringSerializer().deserialize(channel);
        System.out.println("监听到topic为" + topic + "的消息：" + msg);
    }
}
