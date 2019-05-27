package com.example.yyw.util;

import com.example.yyw.constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/7 16:25
 * @describe
 */
public class ResultUtil {
    public static final String SUCCESS_CODE = "1";
    public static final String FAIL_CODE = "0";
    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String FAIL_MESSAGE = "操作失败";

    public static Object successResult() {
        return successResult(null);
    }

    public static Object successResult(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, SUCCESS_CODE);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? SUCCESS_MESSAGE : message);
        return JsonBinder.buildNonNullBinder().toJsonObject(result);
    }

    public static <T> Object successResult(String message, T data) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, SUCCESS_CODE);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? SUCCESS_MESSAGE : message);
        result.put("data", data);
        return JsonBinder.buildNonNullBinder().toJsonObject(result);
    }

    public static <T> Object successResult(T data) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", data);
        result.put(Constant.RESULT_CODE, Constant.SUCCESS_CODE);
        result.put(Constant.RESULT_MESSAGE, Constant.SUCCESS);
        return JsonBinder.buildNonNullBinder().toJsonObject(result);
    }

    public static <T> Object successResultV2(String message, T data) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, SUCCESS_CODE);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? SUCCESS_MESSAGE : message);
        result.put("data", data);
        return JsonBinder.buildNormalBinder().toJsonObject(result);
    }

    public static <T> Object successResultV2(T data) {
        return successResultV2(null,data);
    }

    public static Object errorResult() {
        return errorResult(null);
    }

    public static Object errorResult(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, FAIL_CODE);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? FAIL_MESSAGE : message);
        return JsonBinder.buildNonNullBinder().toJsonObject(result);
    }

    public static Object errorResult(String code, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, StringUtils.isBlank(code) ? FAIL_CODE : code);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? FAIL_MESSAGE : message);
        return JsonBinder.buildNonNullBinder().toJsonObject(result);
    }

    public static <T> Object errorResult(String code, String message, T data) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE, StringUtils.isBlank(code) ? FAIL_CODE : code);
        result.put(Constant.RESULT_MESSAGE, StringUtils.isBlank(message) ? FAIL_MESSAGE : message);
        result.put("data", data);
        return JsonBinder.buildNonNullBinder().toJsonObject(result);
    }

    public static Object commonResult(String code, String message){
        Map<String, Object> result = new HashMap<>();
        result.put(Constant.RESULT_CODE,  code);
        result.put(Constant.RESULT_MESSAGE, message);
        return JsonBinder.buildNonNullBinder().toJsonObject(result);
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
