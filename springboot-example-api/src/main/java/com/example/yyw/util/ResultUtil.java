package com.example.yyw.util;

import com.example.yyw.constant.Constant;
import com.example.yyw.constant.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/7 16:25
 * @describe
 */
public class ResultUtil {
    public static final String SUCCESS_CODE = "1";
    public static final String FAIL_CODE = "0";
    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String FAIL_MESSAGE = "操作失败";

    public static String successResult() {
        return successResult(null);
    }

    public static String successResult(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, SUCCESS_CODE);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? SUCCESS_MESSAGE : message);
        return JsonBinder.buildNonNullBinder().toJson(result);
    }

    public static String successResult(String message, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, SUCCESS_CODE);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? SUCCESS_MESSAGE : message);
        if (null != data) {
            result.put("data", data);
        }
        return JsonBinder.buildNonNullBinder().toJson(result);
    }

    public static String successResult(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, SUCCESS_CODE);
        result.put(Constant.RESULT_MESSAGE, SUCCESS_MESSAGE );
        if (null != data) {
            result.put("data", data);
        }
        return JsonBinder.buildNonNullBinder().toJson(result);
    }

    public static String successResultV2(String message, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, SUCCESS_CODE);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? SUCCESS_MESSAGE : message);
        if (null != data) {
            result.put("data", data);
        }
        return JsonBinder.buildNormalBinder().toJson(result);
    }

    public static String successResultV2(Object data) {
        return successResultV2(null,data);
    }

    public static String errorResult() {
        return errorResult(null);
    }

    public static String errorResult(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, FAIL_CODE);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? FAIL_MESSAGE : message);
        return JsonBinder.buildNonNullBinder().toJson(result);
    }

    public static String errorResult(String code, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, StringUtils.isBlank(code) ? FAIL_CODE : code);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? FAIL_MESSAGE : message);
        return JsonBinder.buildNonNullBinder().toJson(result);
    }

    public static String errorResult(String code, String message, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, StringUtils.isBlank(code) ? FAIL_CODE : code);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? FAIL_MESSAGE : message);
        if (null != data) {
            result.put("data", data);
        }
        return JsonBinder.buildNonNullBinder().toJson(result);
    }

    public static String commonResult(String code, String message){
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE,  code);
        result.put(Constant.RESULT_MESSAGE, message);
        return JsonBinder.buildNonNullBinder().toJson(result);
    }
}
