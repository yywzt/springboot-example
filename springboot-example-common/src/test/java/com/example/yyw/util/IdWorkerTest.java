package com.example.yyw.util;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/10 17:57
 * @describe
 */
public class IdWorkerTest {

    @Test
    public void test_IdWorker() {
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        IdWorker idWorker1 = new IdWorker(0, 0);
        IdWorker idWorker2 = new IdWorker(1, 0);

        for (int i = 0 ;i<20;i++){
            System.out.println("idWork1: " + idWorker1.nextId());
            System.out.println("idWork2: " + idWorker2.nextId());
        }
    }


}