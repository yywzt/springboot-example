package com.example.yyw.simplecat.test;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import org.junit.Test;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/5 14:40
 * @Description
 */
public class TestSender {

    @Test
    public void test() throws Exception {
        Transaction t = Cat.newTransaction("Check1", "name");
        Transaction t3 = Cat.newTransaction("Check2", "name");
        for (int i = 0; i < 2080; i++) {
            Transaction t4 = Cat.newTransaction("Check3", "name");
            t4.complete();
        }
        t3.complete();
        t.complete();

        Thread.sleep(1000); // 此处 sleep 一会, 就能保证 CAT 异步消息发送
    }

}
