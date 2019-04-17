package com.example.yyw.redispubsub.redisHyperLogLog;

import com.example.yyw.redispubsub.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzt
 * @date 2019/2/26 11:07
 * @describe
 */
@RestController
@RequestMapping("/redisHll")
public class redisHll {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/add")
    public void add(@RequestParam("key") String key, @RequestParam("value") Object... value){
        redisUtil.hllAdd(key,value);
    }

    @RequestMapping("/size")
    public Long size(@RequestParam("key") String... key){
        Long aLong = redisUtil.hllSize(key);
        System.out.println("aLong: " + aLong);
        return aLong;
    }

    @RequestMapping("/del")
    public void del(@RequestParam("key") String key){
        redisUtil.hllDelete(key);
    }

    @RequestMapping("/union")
    public Long union(@RequestParam("key") String key, @RequestParam("sourceKey") String... sourceKey){
        Long aLong = redisUtil.hllUnion(key, sourceKey);
        System.out.println("aLong: " + aLong);
        return aLong;
    }

}
