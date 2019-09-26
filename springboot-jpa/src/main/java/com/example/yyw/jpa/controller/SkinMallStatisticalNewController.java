package com.example.yyw.jpa.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.yyw.jpa.modal.SkinMallStatisticalDailyActiveUser;
import com.example.yyw.jpa.modal.SkinMallStatisticalNew;
import com.example.yyw.jpa.repository.SkinMallStatisticalDailyActiveUserRepository;
import com.example.yyw.jpa.repository.SkinMallStatisticalNewRepository;
import io.lettuce.core.ZAddArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/24 20:03
 */
@RestController
@RequestMapping("/skinMall")
public class SkinMallStatisticalNewController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SkinMallStatisticalNewRepository skinMallStatisticalNewRepository;
    @Autowired
    private SkinMallStatisticalDailyActiveUserRepository skinMallStatisticalDailyActiveUserRepository;

    @RequestMapping("/get")
    public void get(){
        Object countError = stringRedisTemplate.opsForValue().get("BYINGLOG_API_APPLY_COUNT_ERROR");
        String key = "NEW_SKIN_MALL_STATISTICAL";
        Set<Object> keys = stringRedisTemplate.opsForHash().keys(key);
        List<Object> objectList = stringRedisTemplate.opsForHash().multiGet(key, keys);
        System.out.println(objectList);

        List<SkinMallStatisticalNew> skinMallStatisticalNewList = new ArrayList<>();
        objectList.stream().forEach(o -> {
            String json = o.toString();
            SkinMallStatisticalNew skinMallStatisticalNew = JSONObject.parseObject(json, SkinMallStatisticalNew.class);
            skinMallStatisticalNewList.add(skinMallStatisticalNew);
        });
        System.out.println(skinMallStatisticalNewList);
        List<SkinMallStatisticalNew> collect = skinMallStatisticalNewList.stream().sorted(Comparator.comparing(SkinMallStatisticalNew::getCreateDate)).collect(Collectors.toList());
        System.out.println(collect);

        skinMallStatisticalNewRepository.saveAll(collect);
    }

    @RequestMapping("/get2")
    public List<SkinMallStatisticalDailyActiveUser> get2(){
        List<SkinMallStatisticalDailyActiveUser> all = skinMallStatisticalDailyActiveUserRepository.findAll();
        System.out.println(all);
        return all;
    }

    @RequestMapping("/get3")
    public Object method_3(){
        Date date = skinMallStatisticalDailyActiveUserRepository.findMaxCreateDateByChannelId("ZA1100");
        System.out.println(date);
        return date;
    }

    @RequestMapping("/get4")
    public Object method_4(String start, String end){
        return skinMallStatisticalDailyActiveUserRepository.findAllByChannelIdAndCreateDateBetweenNative("ZA1100", start, end);
    }
}
