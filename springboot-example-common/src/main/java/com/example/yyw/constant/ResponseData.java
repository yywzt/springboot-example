package com.example.yyw.constant;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/4/23 19:41
 * @describe Title: 统一响应结构
 * Description:使用REST框架实现前后端分离架构，我们需要首先确定返回的JSON响应结构是统一的，
 * 也就是说，每个REST请求将返回相同结构的JSON响应结构。不妨定义一个相对通用的JSON响应结构，其
 * 中包含两部分：元数据与返回值，其中，元数据表示操作是否成功与返回值消息等，返回值对应服务端方法所返回的数据。
 * { "success": true, "message": "ok" , "data": ... }
 */
public class ResponseData<T> {

    private static final String SUCCESS = "1";
    private static final String FAILURE = "-1";

    private String code;
    private String message;
    private T data;

    ResponseData(String code) {
        this.code = code;
    }

    ResponseData(String code, T data) {
        this.code = code;
        this.data = data;
    }

    ResponseData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ResponseData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseData<T> success() {
        return new ResponseData<T>(SUCCESS);
    }

    public static <T> ResponseData<T> success(String message, T data) {
        return new ResponseData<T>(SUCCESS, message, data);
    }

    public static <T> ResponseData<T> success(String message) {
        return new ResponseData<T>(SUCCESS, message);
    }

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<T>(SUCCESS, data);
    }

    public static <T> ResponseData<T> failure() {
        return new ResponseData<T>(FAILURE);
    }

    public static <T> ResponseData<T> failure(String message) {
        return new ResponseData<T>(FAILURE, message);
    }

    public static <T> ResponseData<T> failure(String code, String message) {
        return new ResponseData<T>(code, message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}