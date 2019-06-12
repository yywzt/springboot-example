package com.example.yyw.redis_set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/6/12 23:26
 * @describe
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedisSetSimpleTest {

    @Autowired
    private RedisTemplate redisTemplate;

    public Set<Long> initSet(){
        return new HashSet<Long>(){
            {
                add(1L);
                add(2L);
                add(3L);
                add(5L);
                add(4L);
            }
        };
    }

    private static final String key = "test_set_add";

    @Test
    public void test_set_add(){
        Set<Long> set = initSet();
        redisTemplate.opsForSet().add(key,set.toArray(new Long[0]));
    }

    @Test
    public void test_members(){
        Set members = redisTemplate.opsForSet().members(key);
        log.info("{} - {}",key,members);
    }
    @Test
    public void test_is_members(){
        Boolean isMember = redisTemplate.opsForSet().isMember(key, 1L);
        log.info("{}",isMember);
    }

}
