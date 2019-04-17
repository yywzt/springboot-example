package com.example.yyw.redispubsub.config;

import com.example.yyw.redispubsub.topic.TopicEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/1/10 10:29
 * @desc 绑定监听器与topic配置类
 */
@Configuration
public class PubSubConfig {

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, MessageListener listener){
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(3);
        taskScheduler.initialize();
        listenerContainer.setTaskExecutor(taskScheduler);
        listenerContainer.setConnectionFactory(redisConnectionFactory);
        listenerContainer.addMessageListener(listener,new PatternTopic(TopicEnum.test1.getName()));
        listenerContainer.addMessageListener(listener,new PatternTopic(TopicEnum.test2.getName()));

        return listenerContainer;
    }
}
