package com.example.yyw.repeatSubmit;

import com.example.yyw.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/10/23 15:40
 * @description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepeatSubmitAroundAspectTest {

    @Test
    @Rollback
    public void repeatSubmitBySameParam() throws Exception {
        /**相同参数*/
        String requestUrl = "http://localhost:18081/repeatedSubmit/submit1";
        Map<String, String> map = new HashMap<>();
        map.put("id", "id_one");
        map.put("type", "type_one");

        Map<String, String> map_1 = new HashMap<>();
        map_1.put("id", "id_one");
        map_1.put("type", "type_one");

        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);

        ExecutorService pool = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS,new PriorityBlockingQueue<Runnable>());
        Future<?> future = pool.submit(() -> {
            end.countDown();
            return HttpUtil.httpPost(requestUrl, map);
        });
        Future<?> future_1 = pool.submit(() -> {
            end.countDown();
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return HttpUtil.httpPost(requestUrl, map_1);
        });
        begin.countDown();
        end.await();
        String result = String.valueOf(future.get());
        String result_1 = String.valueOf(future_1.get());
        log.info("responseEntity: {}", result);
        log.info("responseEntity_1: {}", result_1);

        pool.shutdown();

        String expected = "{\"resultCode\":\"1\",\"resultMessage\":\"操作成功\"}";
        String expected_1 = "{\"code\":\"-1\",\"message\":\"DefaultException error : 请勿重复提交\",\"data\":null}";

        JSONAssert.assertEquals(expected, result, true);
        JSONAssert.assertEquals(expected_1, result_1, true);
    }

    @Test
    @Rollback
    public void repeatSubmitByDifferentParam() throws Exception {
        /**不同参数*/
        String requestUrl = "http://localhost:18081/repeatedSubmit/submit1";
        Map<String, String> map = new HashMap<>();
        map.put("id", "id_one");
        map.put("type", "type_one");

        Map<String, String> map_1 = new HashMap<>();
        map_1.put("id", "id_two");
        map_1.put("type", "type_two");

        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);

        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<?> future = pool.submit(() -> {
            HttpUtil.httpPost(requestUrl, map);
            end.countDown();
        });
        Future<?> future_1 = pool.submit(() -> {
            HttpUtil.httpPost(requestUrl, map_1);
            end.countDown();
        });
        begin.countDown();
        end.await();
        String result = String.valueOf(future.get());
        String result_1 = String.valueOf(future_1.get());
        log.info("responseEntity: {}", result);
        log.info("responseEntity_1: {}", result_1);

        String expected = "{\"resultCode\":\"1\",\"resultMessage\":\"操作成功\"}";
        String expected_1 = "{\"resultCode\":\"10048\",\"resultMessage\":\"请勿重复提交!!!\"}";

        JSONAssert.assertEquals(expected, result, true);
        JSONAssert.assertEquals(expected, result_1, true);
    }
}