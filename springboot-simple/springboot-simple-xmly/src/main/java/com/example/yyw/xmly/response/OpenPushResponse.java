package com.example.yyw.xmly.response;

import lombok.Data;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/6/6 10:49
 * @describe
 */
@Data
public class OpenPushResponse {

    private int code;           //推送结果：0-成功，1-失败
    private String message;     //可选，失败时为出错描述
    private String source = "XMLX";      //必填，唯一标识推送接口提供方来源，需要合作方和喜马拉雅共同约定

    private static final int SUCCESS = 0;
    private static final int FAILURE = 1;
    private static final String SUCCESS_MSG = "推送成功";
    private static final String FAILURE_MSG = "推送失败";

    public OpenPushResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static OpenPushResponse success() {
        return OpenPushResponse.success(SUCCESS_MSG);
    }

    public static OpenPushResponse success(String message) {
        return new OpenPushResponse(SUCCESS, message);
    }

    public static OpenPushResponse failure() {
        return OpenPushResponse.failure(FAILURE_MSG);
    }

    public static OpenPushResponse failure(String message) {
        return new OpenPushResponse(FAILURE, message);
    }

}
