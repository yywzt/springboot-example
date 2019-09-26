package com.example.yyw.service;

import com.example.yyw.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/24 11:42
 */
@Slf4j
@Service
public class AsyncService2 {

    @Async
    public void asyncMethod1(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("---asyncMethod1---");
    }

//    @Async
    public void asyncMethod2() throws RuntimeException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("---asyncMethod2---");
    }

}
