package com.example.yyw.service;

import com.example.yyw.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/24 11:42
 */
@Slf4j
@Service
@Transactional
public class AsyncService {

    @Autowired
    private AsyncService2 asyncService2;

    public String test1() {
        log.info("test1 start");
        asyncService2.asyncMethod1();
        asyncService2.asyncMethod2();
        asyncMethod3();
        log.info("test1 end");
        return "true";
    }

    public void asyncMethod3(){
        log.info("---asyncMethod3---");
    }
}
