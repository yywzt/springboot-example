package com.example.yyw.service;

import com.example.yyw.model.ssm.UserInf;
import com.example.yyw.service.ssm.UserInfService;
import com.example.yyw.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    UserInfService userInfService;

    public void tes2(){
        List<UserInf> all = userInfService.findAll();
        log.info("{}", all);
        asyncService2.userInfList();
    }

    public void test3(){
        List<UserInf> all = userInfService.findAll();
        log.info("{}", all);
        AsyncService asyncService = SpringUtils.getBean(AsyncService.class);
        asyncService.listUser();
    }

    @Async
    public Object listUser(){
        List<UserInf> userInfs = userInfService.findAll();
        log.info("async: {}",userInfs);
        return 1;
    }
}
