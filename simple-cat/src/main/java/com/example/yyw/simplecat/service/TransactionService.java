package com.example.yyw.simplecat.service;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import org.springframework.stereotype.Service;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/5 11:49
 * @Description
 */
@Service
public class TransactionService {

    public void pubTransaction() {
        Transaction t = Cat.newTransaction("URL", "pageName");

        try {
            Cat.logEvent("URL.Server", "serverIp", Event.SUCCESS, "ip=${serverIp}");
            Cat.logMetricForCount("metric.key");
            Cat.logMetricForDuration("metric.key", 5);

            t.setStatus(Transaction.SUCCESS);
        } catch (Exception e) {
            t.setStatus(e);
            Cat.logError(e);
        } finally {
            t.complete();
        }
    }
}
