package com.example.yyw.util;

import com.example.yyw.constant.Constant;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/7 16:25
 * @describe
 */
public class ToolUtil {

    public static String checkResult(String code, String msg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESULT_CODE, code);
        result.put(Constant.RESULT_MESSAGE, msg);
        return JsonBinder.buildNonNullBinder().toJson(result);
    }

    public static <T> String successResult(T t) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", t);
        result.put(Constant.RESULT_CODE, Constant.SUCCESS_CODE);
        result.put(Constant.RESULT_MESSAGE, Constant.SUCCESS);
        return JsonBinder.buildNonNullBinder().toJson(result);
    }

    public static <T> String successResult1(T t) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", t);
        result.put(Constant.RESULT_CODE, Constant.SUCCESS_CODE);
        result.put(Constant.RESULT_MESSAGE, Constant.SUCCESS);
        return JsonBinder.buildNormalBinder().toJson(result);
    }

    public static String dataError() {
        Map<String, Object> ret = new HashMap<>();
        ret.put(Constant.RESULT_CODE, Constant.ERROR_2003_CODE);
        ret.put(Constant.RESULT_MESSAGE, Constant.ERROR_2003);
        return JsonBinder.buildNonNullBinder().toJson(ret);
    }

    public static String errorResult(String code,String msg) {
        Map<String, Object> ret = new HashMap<>();
        ret.put(Constant.RESULT_CODE,code);
        ret.put(Constant.RESULT_MESSAGE, msg);
        return JsonBinder.buildNonNullBinder().toJson(ret);
    }

    public static String errorResult(String msg) {
        Map<String, Object> ret = new HashMap<>();
        ret.put(Constant.RESULT_CODE,Constant.ERROR_CODE);
        ret.put(Constant.RESULT_MESSAGE, msg);
        return JsonBinder.buildNonNullBinder().toJson(ret);
    }


    public static String getFlightTotalTime(String start, String end) {
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        try {
            Date detDate = sf.parse(start);
            Date arrDate = sf.parse(end);
            Long time = arrDate.getTime() - detDate.getTime();
            if(time<0){
                time += 24 * 60 * 60 * 1000;
            }
            time = time / 1000;
            int s = (int) (time % 60);
            int m = (int) (time / 60 % 60);
            int h = (int) (time / 3600);
            if (m == 0) {
                return h + "h";
            }
            return h + "h" + m + "m";
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getTrainTotalTime(long time) {
        time = time / 1000;
        int s = (int) (time % 60);
        int m = (int) (time / 60 % 60);
        int h = (int) (time / 3600);
        if (m == 0) {
            return h + "h";
        }
        return h + "h" + m + "m";
    }

    public static Map<String,Object> checkPageResult(Page page, List newList){
        Map<String,Object> result = new HashMap<>();
        if(page!=null){
            result.put("list",newList);
            result.put("currentPage",page.getNumber());
            result.put("totalPages",page.getTotalPages());
            result.put("total",page.getTotalElements());
            result.put("pageSize",page.getSize());
            result.put(Constant.RESULT_CODE, Constant.SUCCESS_CODE);
            result.put(Constant.RESULT_MESSAGE, Constant.SUCCESS);

            return result;
        }else {
            result.put(Constant.RESULT_CODE, Constant.ERROR_1026_CODE_DATA_NULL);
            result.put(Constant.RESULT_MESSAGE, Constant.ERROR_1026);
            return result;
        }
    }
}
