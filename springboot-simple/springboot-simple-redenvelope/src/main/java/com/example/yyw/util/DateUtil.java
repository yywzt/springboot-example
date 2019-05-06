package com.example.yyw.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yanzt
 * @date 2019/4/23 23:13
 * @describe
 */
public class DateUtil {

    public static LocalDateTime now() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    public static Date getNowDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;
    }

    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }

    public static void main(String[] args) {
        Date date = new Date();
        int month = getMonth(date);
        System.out.println(month);
        int year = getYear(date);
        System.out.println(year);

        Date firstDayOfMonth = getFirstDayOfMonth(date);
        System.out.println(firstDayOfMonth);
        System.out.println("getLastDayOfMonth : " + getLastDayOfMonth(date));
    }

    public static int getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }
    public static int getYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static Date getFirstDayOfMonth(Date date){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,getYear(date));
        instance.set(Calendar.MONTH,getMonth(date));
        int actualMinimum = instance.getActualMinimum(Calendar.DAY_OF_MONTH);
        instance.set(Calendar.DAY_OF_MONTH,actualMinimum);
        return instance.getTime();
    }

    public static Date getLastDayOfMonth(Date date){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,getYear(date));
        instance.set(Calendar.MONTH,getMonth(date));
        int actualMinimum = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
        instance.set(Calendar.DAY_OF_MONTH,actualMinimum);
        return instance.getTime();
    }
}
