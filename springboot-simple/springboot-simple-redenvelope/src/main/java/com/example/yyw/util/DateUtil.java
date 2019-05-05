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
    }
}
