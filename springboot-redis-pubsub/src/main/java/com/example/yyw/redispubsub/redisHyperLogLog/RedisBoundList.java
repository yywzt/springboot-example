package com.example.yyw.redispubsub.redisHyperLogLog;

import com.example.yyw.redispubsub.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzt
 * @date 2019/3/1 16:47
 * @describe
 */
@RestController
@RequestMapping("/redisBoundList")
public class RedisBoundList {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/llen")
    public long llen(@RequestParam("key") String key){
        long llen = redisUtil.llen(key);
        System.out.println(llen);
        return llen;
    }

    @RequestMapping("/lpop")
    public void lpop(@RequestParam("key") String key){
        Object lpop = redisUtil.lpop(key);
        System.out.println(lpop);
    }

    @RequestMapping("/rpop")
    public void rpop(@RequestParam("key") String key){
        Object rpop = redisUtil.rpop(key);
        System.out.println(rpop);
    }

    @RequestMapping("/lpush")
    public void lpush(@RequestParam("key") String key, @RequestParam("value") String value){
        redisUtil.lpush(key, value);
    }

    @RequestMapping("/rpush")
    public void rpush(@RequestParam("key") String key, @RequestParam("value") String value){
        redisUtil.rpush(key, value);
    }
}
