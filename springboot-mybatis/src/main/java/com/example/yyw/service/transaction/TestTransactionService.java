package com.example.yyw.service.transaction;

import com.example.yyw.mapper.ssm.TestTransactionMapper;
import com.example.yyw.model.ssm.TestTransaction;
import com.example.yyw.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/6/25 17:54
 * @describe
 */
@Service
@Slf4j
public class TestTransactionService {

    @Autowired
    private TestTransactionMapper testTransactionMapper;

    private TestTransaction initData(){
        int i = RandomUtils.nextInt();
        String testTransactionName = "transaction" + i;
        String testTransactionMsg = "transaction" + i + " msg";
        return new TestTransaction(testTransactionName, testTransactionMsg);
    }

    @Transactional
    public void transation() {
        log.info("method transation start");
        TestTransaction testTransaction = initData();
        int insert = testTransactionMapper.insert(testTransaction);
        log.info("method transation end");
    }

    @Transactional
    public void transation1() {
        log.info("method transation1 start");
        TestTransaction testTransaction = initData();
        int insert = testTransactionMapper.insert(testTransaction);
        log.info("method transation1 end");
    }

    /**
     * 当前情况调用的事务方法事务会失效
     * 生效情况 见 com.example.yyw.service.transaction.NoTransactionService.noTransation2()
     */
    public void noTransation2() {
        transation();
    }

    /**
     * 显示的通过代理对象来调用方法：
     */
    public void noTransation2_1() {
        TestTransactionService testTransactionService = (TestTransactionService)AopContext.currentProxy();
        testTransactionService.transation();
    }

    /**
     * 也可解决事务失效
     */
    public void noTransation2_2() {
        TestTransactionService testTransactionService = SpringUtils.getBean(this.getClass());
        testTransactionService.transation();
    }
}
