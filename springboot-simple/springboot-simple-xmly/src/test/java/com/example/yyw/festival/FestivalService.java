package com.example.yyw.festival;

import com.example.yyw.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tk.mybatis.mapper.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/7 11:10
 * @describe
 */
@Slf4j
public class FestivalService {

    //阿里万年历查询URL
    //public static final String CALENDAR_QUERY_URL="http://jisuwnl.market.alicloudapi.com/calendar/query";
    //sojson网站免费接口 https://www.sojson.com
    public static final String CALENDAR_QUERY_URL="https://www.sojson.com/open/api/lunar/json.shtml";

    @Test
    public void test1(){
        String date = "2019-05-01";
        String s = HttpUtil.httpGet(CALENDAR_QUERY_URL + "?date=" + date);
        log.info("response: {}",s);
    }

    @Test
    public void test2() throws ParseException {
//        String currentDateStr = "";
        String currentDateStr = "2019-05-01";
        Date currentDate = null;
        if(StringUtil.isEmpty(currentDateStr)){
            currentDate = new Date();
        }else {
            currentDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentDateStr);
        }
        System.out.println(currentDate);
        java.sql.Date date = java.sql.Date.valueOf(currentDateStr);
        System.out.println(date);
        System.out.println(getLastDayOfYear(date));
        int month = getMonth(date);
        System.out.println(month);
    }

    //获取某时间的年末末最后一天时间
    public static Date getLastDayOfYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,getYear(date));
        int maxMonth = cal.getActualMaximum(Calendar.MONTH);
        cal.set(Calendar.MONTH,maxMonth);
        //获取某月最大天数
        int actualMaximum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH,actualMaximum);
        return cal.getTime();
    }

    private static int getYear(Date date) {
        return getInteger(date,Calendar.YEAR);
    }
    private static int getMonth(Date date) {
        return getInteger(date,Calendar.MONTH);
    }

    private static int getInteger(Date date, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateType);
    }

    private static Date init(int year,int month,int day){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,year);
        instance.set(Calendar.MONTH,month);
        instance.set(Calendar.DAY_OF_MONTH,day);
        return instance.getTime();
    }

    @Test
    public void test3(){
        log.info("time : {}",init(2019,05,01));
        log.info("month : {}",getMonth(new Date()));
        Calendar instance = Calendar.getInstance();
        int maxMonth = instance.getActualMaximum(Calendar.MONTH);
        log.info("max month: {}",maxMonth);
    }
}
