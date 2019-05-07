package com.example.yyw.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/7 16:25
 * @describe
 */
public class TimeUtil {

    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter ISO_LOCAL_DATE_TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME; //2019-05-07T17:00:11.225
    private static final DateTimeFormatter ISO_LOCAL_DATE = DateTimeFormatter.ISO_LOCAL_DATE; //2019-05-07
    private static final DateTimeFormatter ISO_LOCAL_TIME = DateTimeFormatter.ISO_LOCAL_TIME; //17:00:11.225

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DEFAULT_DATE_TIME_FORMATTER);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return localDateTime.format(dateTimeFormatter);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, int type) {
        switch (type) {
            case 1:
                return localDateTimeToString(localDateTime, ISO_LOCAL_DATE_TIME);
            case 2:
                return localDateTimeToString(localDateTime, ISO_LOCAL_DATE);
            case 3:
                return localDateTimeToString(localDateTime, ISO_LOCAL_TIME);
            default:
                return localDateTimeToString(localDateTime);
        }
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * 获得某时间的当月第一天
     *
     * @param localDateTime
     * @return LocalDate
     */
    public static LocalDate getFirstDayOfMonth(LocalDateTime localDateTime) {
        localDateTime.getMonth();
        int year = getYear(localDateTime);
        int month = getMonth(localDateTime);
        int day = 1;
        return LocalDate.of(year, month, day);
    }

    /**
     * 获得某时间的当月最后一天
     *
     * @param localDateTime
     * @return LocalDate
     */
    public static LocalDate getLastDayOfMonth(LocalDateTime localDateTime) {
        localDateTime.getMonth();
        int year = getYear(localDateTime);
        int month = getMonth(localDateTime);
        boolean leap = Year.isLeap(year);
        int day = localDateTime.getMonth().length(leap);
        return LocalDate.of(year, month, day);
    }

    /**
     * 获取年份
     *
     * @param localDateTime
     * @return
     */
    public static int getYear(LocalDateTime localDateTime) {
        return localDateTime.getYear();
    }

    /**
     * 获取月份
     *
     * @param localDateTime
     * @return
     */
    public static int getMonth(LocalDateTime localDateTime) {
        return localDateTime.getMonthValue();
    }

    /**
     * 返回此{@code LocalDateTime}的副本，并添加指定的年数
     *
     * @param localDateTime
     * @param years
     * @return
     */
    public static LocalDateTime plusYears(LocalDateTime localDateTime, long years) {
        return localDateTime.plusYears(years);
    }

    /**
     * 返回此{@code LocalDateTime}的副本，并添加指定的月份数
     *
     * @param localDateTime
     * @param months
     * @return
     */
    public static LocalDateTime plusMonths(LocalDateTime localDateTime, long months) {
        return localDateTime.plusMonths(months);
    }

    /**
     * 返回此{@code LocalDateTime}的副本，并添加指定的天数
     *
     * @param localDateTime
     * @param days
     * @return
     */
    public static LocalDateTime plusDays(LocalDateTime localDateTime, long days) {
        return localDateTime.plusDays(days);
    }

}
