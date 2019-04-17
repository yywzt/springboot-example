package com.example.yyw.redispubsub;

import com.example.yyw.redispubsub.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yanzt
 * @date 2019/4/16 21:59
 * @describe
 *      在 Redis 中，常用的 5 种数据结构和应用场景如下：
 *          String：缓存、计数器、分布式锁等。
 *          List：链表、队列、微博关注人时间轴列表等。
 *          Hash：用户信息、Hash 表等。
 *          Set：去重、赞、踩、共同好友等。
 *          Zset：访问量排行榜、点击量排行榜等
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testSetIfAbsent(){
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("setIfAbsent", "aaa", Duration.ofSeconds(30));
        System.out.println(aBoolean);
        aBoolean = redisTemplate.opsForValue().setIfAbsent("setIfAbsent","aaa", 30,TimeUnit.SECONDS);
        System.out.println(aBoolean);
    }
    @Test
    public void testSetIfPresent(){
        Boolean aBoolean = redisTemplate.opsForValue().setIfPresent("testSetIfPresent", "aaa",30,TimeUnit.SECONDS);
        System.out.println(aBoolean);
        aBoolean = redisTemplate.opsForValue().setIfPresent("testSetIfPresent","aaa",Duration.ofSeconds(30));
        System.out.println(aBoolean);
    }

    public static List<String> stringList = new ArrayList<>();
    public static List<String> stringList2 = new ArrayList<>();

    public void initListData(){
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
    }
    public void initListData2(){
        stringList2.add("a");
        stringList2.add("b");
        stringList2.add("c");
        stringList2.add("d");
        stringList2.add("e");
    }

    @Test
    public void testListLeftPush(){
        initListData();
        initListData2();
        redisTemplate.opsForList().leftPush("listLeftPush",stringList);
        List<Object> list1 = redisUtil.lGet("listLeftPush", 0, -1);
        redisTemplate.opsForList().rightPush("listLeftPush",stringList2);
        list1 = redisUtil.lGet("listLeftPush", 0, -1);
        redisTemplate.opsForList().leftPush("listLeftPush",stringList2);
        list1 = redisUtil.lGet("listLeftPush", 0, -1);
        System.out.println(list1);
    }

    @Test
    public void testSet(){
        redisTemplate.opsForSet().add("set1","b","a","c","a");
        Set set1 = redisTemplate.opsForSet().members("set1");
        System.out.println(set1);
        Boolean member = redisTemplate.opsForSet().isMember("set1","a");
        System.out.println(member);
        member = redisTemplate.opsForSet().isMember("set1","d");
        System.out.println(member);
    }

    @Test
    public void testZset(){
        HashSet<ZSetOperations.TypedTuple<String>> hashSet = new HashSet<ZSetOperations.TypedTuple<String>>();
        hashSet.add(new DefaultTypedTuple<>("index1",12300d));
        hashSet.add(new DefaultTypedTuple<>("index2",22300d));
        hashSet.add(new DefaultTypedTuple<>("index3",12000d));
        hashSet.add(new DefaultTypedTuple<>("index4",19300d));
        hashSet.add(new DefaultTypedTuple<>("index5",122300d));
//        redisTemplate.opsForZSet().add("zset1","a",2.2d);
        redisTemplate.opsForZSet().add("zset1",hashSet);
        Set zset1 = redisTemplate.opsForZSet().range("zset1", 0, -1);
        System.out.println(zset1);
        Long rank = redisTemplate.opsForZSet().rank("zset1", "index1");
        System.out.println(rank);
        //降序
        rank = redisTemplate.opsForZSet().reverseRank("zset1", "index1");
        System.out.println(rank);
    }
}
