package com.example.yyw.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/6/17 17:57
 * @describe
 */
@Slf4j
public class DateUtilTest {

    @Test
    public void getLastHourOfDay() {
        Date init = DateUtil.init(2019, 04, 16);
        log.info("{}",init);
        Date lastHourOfDay = DateUtil.getLastHourOfDay(init);
        Assert.assertEquals(lastHourOfDay,DateUtil.init(2019, 04, 16, 23, 59, 59, 999));

        init = DateUtil.init(2019, 05, 17);
        log.info("{}",init);
        lastHourOfDay = DateUtil.getLastHourOfDay(init);
        Assert.assertEquals(lastHourOfDay,DateUtil.init(2019, 05, 17, 23, 59, 59, 999));
    }
}