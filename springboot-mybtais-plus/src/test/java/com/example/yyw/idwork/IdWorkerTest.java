package com.example.yyw.idwork;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.Test;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/10 17:29
 * @describe
 */
public class IdWorkerTest {

    @Test
    public void test_1(){
        long id = IdWorker.getId();
        String idStr = IdWorker.getIdStr();
        String uuid = IdWorker.get32UUID();
        String millisecond = IdWorker.getMillisecond();
        String timeId = IdWorker.getTimeId();
        System.out.println("id: " + id);
        System.out.println("idStr: " + idStr);
        System.out.println("uuid: " + uuid);
        System.out.println("millisecond: " + millisecond);
        System.out.println("timeId: " + timeId);
    }
}
