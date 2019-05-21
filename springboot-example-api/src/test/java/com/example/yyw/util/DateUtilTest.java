package com.example.yyw.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/7 14:30
 * @describe
 */
@Slf4j
public class DateUtilTest {

    private static final Date DATE = new Date();

    @Test
    public void getNowDate() {
        Date nowDate = DateUtil.getNowDate();
        log.info("Date now: {}",nowDate);
    }

    @Test
    public void getFirstDayOfMonth1() {
        log.info("DATE firstDayOfMonth : {}",DateUtil.getFirstDayOfMonth(DATE));
    }

    @Test
    public void getLastDayOfMonth() {
        log.info("DATE lastDayOfMonth : {}",DateUtil.getLastDayOfMonth(DATE));
    }

    @Test
    public void getLastDayOfYear() {
        log.info("DATE lastDayOfYear : {}",DateUtil.getLastDayOfYear(DATE));
    }

}
