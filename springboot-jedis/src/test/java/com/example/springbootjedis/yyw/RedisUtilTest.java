package com.example.springbootjedis.yyw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {
    private static final String key = "xmlx_redis_test_001";
    private static final String key2 = "xmlx_redis_test_002";
    private static final String OLDKEY = "xmlx_redis_test_001_set_old";
    private static final String NEWKEY = "xmlx_redis_test_001_set_new";
    private static final String value = UUID.randomUUID().toString();

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void set() {
        redisUtil.set(key, value);
        assertEquals(value, redisUtil.get(key));
        //修改
        String newValue = UUID.randomUUID().toString();
        redisUtil.set(key, newValue);
        assertEquals(newValue, redisUtil.get(key));
    }

    @Test
    public void get() {
        assertTrue(null == redisUtil.get(UUID.randomUUID().toString()));
    }

    @Test
    public void hset() {
        String key = "students_3_1";
        Map<String, String> map = new HashMap<String, String>() {{
            put("name", "张三");
            put("score", "88");
            put("gender", "1");
        }};
        redisUtil.hset(key, map);
        Map<String, String> queryMap = redisUtil.hget(key);
        assertEquals("张三", queryMap.get("name"));
        assertEquals("88", queryMap.get("score"));
        assertEquals("1", queryMap.get("gender"));

        assertEquals("张三", redisUtil.hget(key, "name"));
        assertEquals("88", redisUtil.hget(key, "score"));
        assertEquals("1", redisUtil.hget(key, "gender"));

        redisUtil.hset(key, "age", "16");
        assertEquals("16", redisUtil.hget(key, "age"));
        redisUtil.hset(key, "weight", "80");
        assertEquals("80", redisUtil.hget(key, "weight"));
    }

    @Test
    public void hget() {
        assertTrue(null == redisUtil.hget(UUID.randomUUID().toString()));

        assertTrue(null == redisUtil.hget(UUID.randomUUID().toString(), "name"));
        assertTrue(null == redisUtil.hget(UUID.randomUUID().toString(), "score"));
        assertTrue(null == redisUtil.hget(UUID.randomUUID().toString(), "gender"));
    }

    @Test
    public void expire() {
        redisUtil.set(key, value);
        System.out.println(value);
        redisUtil.expire(key, 1);
        assertEquals(value, redisUtil.get(key));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(null, redisUtil.get(key));
    }

    @Test
    public void expireAt() {
        redisUtil.set(key, value);
        redisUtil.expireAt(key, buildDateAfterSeconds(1));
        assertEquals(value, redisUtil.get(key));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(null, redisUtil.get(key));
    }

    private Date buildDateAfterSeconds(int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    @Test
    public void smembers(){
        assertTrue(0 == redisUtil.smembers(UUID.randomUUID().toString()).size());
    }

    private static final Set<String> strings = new HashSet<String>() {{
        add("1");
        add("2");
        add("3");
    }};
    @Test
    public void sadd(){
        redisUtil.sadd(OLDKEY,strings.stream().toArray(String[]::new));
        assertEquals(strings, redisUtil.smembers(OLDKEY));
    }
    @Test
    public void srem(){
        redisUtil.sadd(OLDKEY,strings.stream().toArray(String[]::new));
        String str = "1";
        redisUtil.srem(OLDKEY, str);
        assertTrue(!redisUtil.smembers(OLDKEY).contains(str));
        assertTrue(redisUtil.smembers(OLDKEY).contains("2"));
        assertTrue(redisUtil.smembers(OLDKEY).contains("3"));
    }

    @Test
    public void rename(){
        redisUtil.del(NEWKEY);
        redisUtil.sadd(OLDKEY,strings.stream().toArray(String[]::new));
        assertTrue(redisUtil.keys(OLDKEY).contains(OLDKEY));
        assertTrue(!redisUtil.keys(NEWKEY).contains(NEWKEY));

        redisUtil.rename(OLDKEY, NEWKEY);
        assertTrue(!redisUtil.keys(OLDKEY).contains(OLDKEY));
        assertTrue(redisUtil.keys(NEWKEY).contains(NEWKEY));
    }
    @Test
    public void del(){
        redisUtil.sadd(OLDKEY,strings.stream().toArray(String[]::new));
        redisUtil.sadd(NEWKEY,strings.stream().toArray(String[]::new));
        assertEquals(strings, redisUtil.smembers(OLDKEY));
        assertEquals(strings, redisUtil.smembers(NEWKEY));

        redisUtil.del(OLDKEY);
        redisUtil.del(NEWKEY);
        assertTrue( 0 == redisUtil.smembers(OLDKEY).size());
        assertTrue(0 == redisUtil.smembers(NEWKEY).size());
    }
    @Test
    public void keys(){
        redisUtil.sadd(OLDKEY,strings.stream().toArray(String[]::new));
        redisUtil.sadd(NEWKEY,strings.stream().toArray(String[]::new));
        redisUtil.sadd(key2,strings.stream().toArray(String[]::new));
        Set<String> keys = redisUtil.keys(key);
        assertTrue(keys.contains(NEWKEY));
        assertTrue(keys.contains(OLDKEY));
        assertTrue(!keys.contains(key2));
    }

    public static final String XIMALAYA_CACHE_COMMON_PREFIX = "XIMALAYA:";
    public static final String XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_TO_TRACK_ID_CACHE_PREFIX = XIMALAYA_CACHE_COMMON_PREFIX + "EOCI_TI:";
    public static final String XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_LIST_CACHE_PREFIX = XIMALAYA_CACHE_COMMON_PREFIX + "EOCI_LIST";

    @Test
    public void scan(){
        List<String> scan = redisUtil.scans(XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_TO_TRACK_ID_CACHE_PREFIX + "*", 50);
        List<String> scan1 = redisUtil.scans(XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_LIST_CACHE_PREFIX + "*", 50);
        System.out.println(scan);
        System.out.println(scan.size());
        System.out.println(scan1);
        System.out.println(scan1.size());
    }
}