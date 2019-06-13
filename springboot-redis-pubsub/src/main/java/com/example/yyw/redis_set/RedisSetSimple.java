package com.example.yyw.redis_set;

import com.example.yyw.redispubsub.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/6/12 23:23
 * @describe
 */
@RestController
@RequestMapping("/testRtSet")
public class RedisSetSimple {

    @Autowired
    private RedisUtil redisUtil;

    private static final String keys = "test_set_add";

    @RequestMapping("/addSet")
    public void addSet(String key, String... value){
        redisUtil.sSet(key, value);
    }
    @RequestMapping("/addSet2")
    public void addSet2(String key, Long... value){
        redisUtil.sSet2(key, value);
    }


    @RequestMapping("/members")
    public Object members(String key){
        return redisUtil.members(key);
    }

    @RequestMapping("/isMember")
    public boolean isMember(String key, String value){
        return redisUtil.isMember(key, value);
    }

    @RequestMapping("/remove")
    public long remove(String key, String... value){
        return redisUtil.setRemove(key, value);
    }

}
