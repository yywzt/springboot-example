package com.example.yyw.schedu;

import com.example.yyw.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/27 16:46
 */
@Slf4j
//@Component
public class ScheduingBean {

    /**每五秒执行一次*/
    @Async
    @Scheduled(cron = "0/5 * * * * * ")
    public void scheduled(){
        log.info("--->使用corn {} ",System.currentTimeMillis());
    }

    /**
     * 以固定的时间执行任务
     * */
    @Scheduled(fixedRate = 5000)
    public  void scheduled1(){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("--->fixedRate {} {}",System.currentTimeMillis(), TimeUtil.localDateTimeToString(LocalDateTime.now(),1));
    }

    /**
     * 前一次任务与后一次任务相隔的时间
     * */
    @Scheduled(fixedDelay = 5000)
    public  void scheduled2(){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("--->fixedDelay {} {} ",System.currentTimeMillis(), TimeUtil.localDateTimeToString(LocalDateTime.now(),1));
    }
}
