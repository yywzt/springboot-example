package com.example.yyw;

import com.example.yyw.redis_map.SkinMallStatisticalVo;
import com.example.yyw.redispubsub.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisPubsubApplicationTests {


    @Test
    public void contextLoads() {

    }

    private static final String key = "xmlx_redis_test_001";
    private static final String key2 = "xmlx_redis_test_002";
    private static final String OLDKEY = "xmlx_redis_test_001_set_old";
    private static final String NEWKEY = "xmlx_redis_test_001_set_new";
    private static final String value = UUID.randomUUID().toString();

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void smembers(){
        assertTrue(null == redisUtil.members(UUID.randomUUID().toString()));
    }
    @Test
    public void smembers1(){
        Set<Object> members = redisUtil.members(UUID.randomUUID().toString());
        System.out.println(members);
    }

    private static final Set<String> strings = new HashSet<String>() {{
        add("1");
        add("2");
        add("3");
    }};
    @Test
    public void sadd(){
        redisUtil.sSet(OLDKEY,strings.stream().toArray(String[]::new));
        assertEquals(strings, redisUtil.members(OLDKEY));
    }
    @Test
    public void srem(){
        redisUtil.sSet(OLDKEY,strings.stream().toArray(String[]::new));
        String str = "1";
        redisUtil.setRemove(OLDKEY, str);
        assertTrue(!redisUtil.members(OLDKEY).contains(str));
        assertTrue(redisUtil.members(OLDKEY).contains("2"));
        assertTrue(redisUtil.members(OLDKEY).contains("3"));
    }

    @Test
    public void rename(){
        redisUtil.sSet(OLDKEY,strings.stream().toArray(String[]::new));
        assertTrue(redisUtil.keys(OLDKEY).contains(OLDKEY));
        assertTrue(!redisUtil.keys(NEWKEY).contains(NEWKEY));

        redisUtil.rename(OLDKEY, NEWKEY);
        assertTrue(!redisUtil.keys(OLDKEY).contains(OLDKEY));
        assertTrue(redisUtil.keys(NEWKEY).contains(NEWKEY));
    }
    @Test
    public void del(){
        redisUtil.sSet(OLDKEY,strings.stream().toArray(String[]::new));
        redisUtil.sSet(NEWKEY,strings.stream().toArray(String[]::new));
        assertEquals(strings, redisUtil.members(OLDKEY));
        assertEquals(strings, redisUtil.members(NEWKEY));

        redisUtil.del(OLDKEY);
        redisUtil.del(NEWKEY);
        assertTrue( null == redisUtil.members(OLDKEY));
        assertTrue(null == redisUtil.members(NEWKEY));
    }
    @Test
    public void keys(){
        redisUtil.sSet(OLDKEY,strings.stream().toArray(String[]::new));
        redisUtil.sSet(NEWKEY,strings.stream().toArray(String[]::new));
        redisUtil.sSet(key2,strings.stream().toArray(String[]::new));
        Set<String> keys = redisUtil.keys(key);
        assertTrue(keys.contains(NEWKEY));
        assertTrue(keys.contains(OLDKEY));
        assertTrue(!keys.contains(key2));
    }

    @Test
    public void test_hSet(){
        String key = "hset";
        SkinMallStatisticalVo skinMallStatisticalVo = new SkinMallStatisticalVo();
        skinMallStatisticalVo.setUserCount(222);
        skinMallStatisticalVo.setEnterCount(333);
        skinMallStatisticalVo.setPayCount(444);
        skinMallStatisticalVo.setUserAddCount(555);
        skinMallStatisticalVo.setThemeCount(666);
        skinMallStatisticalVo.setDetailsCount(777);
        skinMallStatisticalVo.setDetailsCount(888);
        skinMallStatisticalVo.setDetailsCount(999);
        Map<String,Object> map = new HashMap<>(1);
        map.put("2019-07-17", skinMallStatisticalVo);
        map.put("2019-07-18", skinMallStatisticalVo);
        redisTemplate.opsForHash().putAll(key, map);

        Set keys = redisTemplate.opsForHash().keys(key);
        System.out.println(keys);

        SkinMallStatisticalVo vo = (SkinMallStatisticalVo) redisTemplate.opsForHash().get(key, "2019-07-17");
        System.out.println(vo);
    }
}
