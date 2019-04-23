package com.example.yyw.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yanzt
 * @date 2019/4/23 23:13
 * @describe
 */
public class DateUtil {

    public static Timestamp getNowTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date getNowDate(){
        Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        return date;
    }
}
