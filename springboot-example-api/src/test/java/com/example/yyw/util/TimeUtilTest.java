package com.example.yyw.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/7 16:28
 * @describe
 */
@Slf4j
public class TimeUtilTest {

    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();

    @Test
    public void localDateTimeToString1() {
        String time = TimeUtil.localDateTimeToString(LOCAL_DATE_TIME);
        log.info("string time: {}",time);
    }
    @Test
    public void localDateTimeToString2() {
        String time = TimeUtil.localDateTimeToString(LOCAL_DATE_TIME, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        log.info("string time: {}",time);
    }

    @Test
    public void now() {
        LocalDateTime now = TimeUtil.now();
        log.info("LocalDateTime now: {}",now);
    }

    @Test
    public void getFirstDayOfMonth() {
        log.info("LOCAL_DATE_TIME firstDayOfMonth : {}",TimeUtil.getFirstDayOfMonth(LOCAL_DATE_TIME));
    }

    @Test
    public void getLastDayOfMonth() {
        log.info("LOCAL_DATE_TIME lastDayOfMonth : {}",TimeUtil.getLastDayOfMonth(LOCAL_DATE_TIME));
    }

    @Test
    public void test1(){
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2019, 02, 01), LocalTime.of(0, 0, 0));
        localDateTime.getMonth();
        int year = TimeUtil.getYear(localDateTime);
        int month = TimeUtil.getMonth(localDateTime);
        boolean leap = Year.isLeap(year);
        int day = localDateTime.getMonth().length(leap);
        LocalDate of = LocalDate.of(year, month, day);
        log.info("LoaclDate : {}",of);
    }

    @Test
    public void plusYears() {
        LocalDateTime localDateTime = TimeUtil.plusYears(LOCAL_DATE_TIME, 1L);
        log.info("before : {}", TimeUtil.localDateTimeToString(LOCAL_DATE_TIME,DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        log.info("after : {}", TimeUtil.localDateTimeToString(localDateTime,DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
    @Test
    public void plusMonths() {
        LocalDateTime localDateTime = TimeUtil.plusMonths(LOCAL_DATE_TIME, 1L);
        log.info("before : {}", TimeUtil.localDateTimeToString(LOCAL_DATE_TIME,DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        log.info("after : {}", TimeUtil.localDateTimeToString(localDateTime,DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
    @Test
    public void plusDays() {
        LocalDateTime localDateTime = TimeUtil.plusDays(LOCAL_DATE_TIME, 1L);
        log.info("before : {}", TimeUtil.localDateTimeToString(LOCAL_DATE_TIME,DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        log.info("after : {}", TimeUtil.localDateTimeToString(localDateTime,DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Test
    public void localDateTimeToStringOfType(){
        for (int i=1;i<=6;i++){
            log.info("localDateTimeToStringOfType-{} : {}",i,TimeUtil.localDateTimeToString(LOCAL_DATE_TIME,i));
        }
    }
}
