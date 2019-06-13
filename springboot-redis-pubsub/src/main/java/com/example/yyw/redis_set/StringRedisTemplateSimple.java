package com.example.yyw.redis_set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/6/13 15:36
 * @describe
 */
@RestController
@RequestMapping("/testSrtSet")
public class StringRedisTemplateSimple {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/addSet")
    public Long addSet(String key, String... value) {
        List<String> stringList = new ArrayList<>(100000);
        for (int i = 1; i <= 100000; i++) {
            stringList.add(String.valueOf(i));
        }
        return stringRedisTemplate.opsForSet().add(key, stringList.toArray(new String[stringList.size()]));
    }

    @RequestMapping("/members")
    public Set<String> members(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    @RequestMapping("/isMember")
    public boolean isMember(String key, String value) {
        return stringRedisTemplate.opsForSet().isMember(key, value);
    }

    @RequestMapping("/remove")
    public long remove(String key, String... value) {
        return stringRedisTemplate.opsForSet().remove(key, value);
    }

    @RequestMapping("/pop")
    public String pop(String key) {
        return stringRedisTemplate.opsForSet().pop(key);
    }

    @RequestMapping("/popCount")
    public List<String> popCount(String key, long count) {
        return stringRedisTemplate.opsForSet().pop(key, count);
    }

    @RequestMapping("/move")
    public boolean move(String key, String value, String newKey) {
        return stringRedisTemplate.opsForSet().move(key, value, newKey);
    }

    /**
     * 当 OLD_KEY_NAME 和 NEW_KEY_NAME 相同，或者 OLD_KEY_NAME 不存在时，返回一个错误。
     * 当 NEW_KEY_NAME 已经存在时， RENAME 命令将覆盖旧值。
     *
     * @param oldKey
     * @param newKey
     */
    @RequestMapping("/rename")
    public void rename(String oldKey, String newKey) {
        stringRedisTemplate.rename(oldKey, newKey);
    }

    /**
     * Redis Renamenx 命令用于在新的 key 不存在时修改 key 的名称 。
     *
     * @param oldKey
     * @param newKey
     * @return
     */
    @RequestMapping("/renameIfAbsent")
    public boolean renameIfAbsent(String oldKey, String newKey) {
        return stringRedisTemplate.renameIfAbsent(oldKey, newKey);
    }

}
