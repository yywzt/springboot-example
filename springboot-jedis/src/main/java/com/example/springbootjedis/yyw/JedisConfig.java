package com.example.springbootjedis.yyw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/6/20 11:37
 * @describe
 */
@Configuration
public class JedisConfig {

    private JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private int database;

    @Bean
    public JedisPool jedisPool(){
        return new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
    }
}
