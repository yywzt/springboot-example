package com.example.yyw.service.transaction;

import com.example.yyw.mapper.ssm.TestTransactionMapper;
import com.example.yyw.model.ssm.TestTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/6/25 19:21
 * @describe
 */
@Service
@Slf4j
public class NoTransactionService {

    @Autowired
    private TestTransactionMapper testTransactionMapper;
    @Autowired
    private TestTransactionService testTransactionService;

    private TestTransaction initData(){
        int i = RandomUtils.nextInt();
        String testTransactionName = "transaction" + i;
        String testTransactionMsg = "transaction" + i + " msg";
        return new TestTransaction(testTransactionName, testTransactionMsg);
    }

    public void noTransation1() {
        log.info("method no transation1 start");
        TestTransaction testTransaction = initData();
        int insert = testTransactionMapper.insert(testTransaction);
        log.info("method no transation1 end");
    }

    /**
     * 当前情况调用的事务方法事务会生效
     */
    public void noTransation2() {
        testTransactionService.transation();
    }
}
