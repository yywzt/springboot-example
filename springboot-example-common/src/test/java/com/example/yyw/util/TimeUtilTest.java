package com.example.yyw.util;

import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/8 17:21
 * @describe
 */
public class TimeUtilTest {

    @Test
    public void converToDate() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.JULY, 8, 17, 23, 24, 666000000);
        Date date = TimeUtil.converToDate(localDateTime);
        assertEquals(DateUtil.dateToString(date), TimeUtil.localDateTimeToString(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        assertEquals(date.getTime(), TimeUtil.getEpochMilli(localDateTime));
    }

    @Test
    public void converToLocalDateTime() {
        Date date = new Date();
        LocalDateTime localDateTime = TimeUtil.converToLocalDateTime(date);
        assertEquals(DateUtil.dateToString(date), TimeUtil.localDateTimeToString(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        assertEquals(date.getTime(), TimeUtil.getEpochMilli(localDateTime));
    }
}