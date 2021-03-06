package com.example.yyw.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/7 16:25
 * @describe
 */
@Slf4j
public class DateUtil {

    public static String dateToString(Date date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            log.error("dateToString error : {}", e.getMessage());
            return "";
        }
    }

    public static Date stringToDate(String date, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(date);
        } catch (Exception e) {
            log.error("stringToDate error : {}", e.getMessage());
            return null;
        }
    }

    public static Date getNowDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获得某时间的当月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, getYear(date));
        instance.set(Calendar.MONTH, getMonth(date));
        int actualMinimum = instance.getActualMinimum(Calendar.DAY_OF_MONTH);
        instance.set(Calendar.DAY_OF_MONTH, actualMinimum);
        return instance.getTime();
    }

    /**
     * 获得某时间的当月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, getYear(date));
        instance.set(Calendar.MONTH, getMonth(date));
        int actualMinimum = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
        instance.set(Calendar.DAY_OF_MONTH, actualMinimum);
        return instance.getTime();
    }

    /**
     * 获取某时间的年末最后一天时间
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getYear(date));
        int maxMonth = cal.getActualMaximum(Calendar.MONTH);
        cal.set(Calendar.MONTH, maxMonth);
        //获取某月最大天数
        int actualMaximum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, actualMaximum);
        return cal.getTime();
    }

    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH);
    }

    private static int getInteger(Date date, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateType);
    }

    public static Date init(int year, int month, int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, year);
        instance.set(Calendar.MONTH, month);
        instance.set(Calendar.DAY_OF_MONTH, day);
        return instance.getTime();
    }

    public static Date init(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, year);
        instance.set(Calendar.MONTH, month);
        instance.set(Calendar.DAY_OF_MONTH, day);
        instance.set(Calendar.HOUR_OF_DAY, hour);
        instance.set(Calendar.MINUTE, minute);
        instance.set(Calendar.SECOND, second);
        instance.set(Calendar.MILLISECOND, millisecond);
        return instance.getTime();
    }

    public static void main(String[] args) {
//        Date date = new Date();
//        int month = getMonth(date);
//        System.out.println(month);
//        int year = getYear(date);
//        System.out.println(year);
//
//        Date firstDayOfMonth = getFirstDayOfMonth(date);
//        System.out.println(firstDayOfMonth);
//        System.out.println("getLastDayOfMonth : " + getLastDayOfMonth(date));
//
//        Date date1 = addMinute(date, 10);
//        System.out.println(date1);

        Date lastHourOfDay = getLastHourOfDay(new Date());
        System.out.println(lastHourOfDay);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(lastHourOfDay);
        System.out.println(format);
    }

    public static Date addMinute(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, i);
        return calendar.getTime();
    }

    /**
     * 获得某时间的当天最后一刻
     *
     * @param date
     * @return
     */
    public static Date getLastHourOfDay(Date date) {
        if(date == null){
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MILLISECOND, 999);
        return instance.getTime();
    }
}
