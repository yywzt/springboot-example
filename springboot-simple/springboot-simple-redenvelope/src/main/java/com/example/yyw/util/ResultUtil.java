package com.example.yyw.util;

import com.example.yyw.constant.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanzt
 * @date 2019/4/24 23:31
 * @describe
 */
public class ResultUtil {

    public static final int SUCCESS_CODE = 1;
    public static final int FAIL_CODE = 0;

    public static String successResult() {
        return successResult(null);
    }

    public static String successResult(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.RESULT_CODE, Constants.SUCCESS_CODE);
        result.put(Constants.RESULT_MESSAGE, StringUtils.isBlank(message) ? Constants.SUCCESS : message);
        return JsonBinder.buildNonNullBinder().toJson(result);
    }

    public static String successResult1(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.RESULT_CODE, Constants.SUCCESS_CODE);
        result.put(Constants.RESULT_MESSAGE, Constants.SUCCESS);
        result.put("data", data);
        return JsonBinder.buildNonNullBinder().toJson(result);
    }


    public static String errorResult() {
        return errorResult(null);
    }


    public static String errorResult(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.RESULT_CODE, FAIL_CODE);
        result.put(Constants.RESULT_MESSAGE, StringUtils.isBlank(message) ? Constants.FAILURE : message);
        return JsonBinder.buildNonNullBinder().toJson(result);
    }

    public static String errorResult(String code, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.RESULT_CODE, code);
        result.put(Constants.RESULT_MESSAGE, StringUtils.isBlank(message) ? Constants.FAILURE : message);
        return JsonBinder.buildNonNullBinder().toJson(result);
    }
}
