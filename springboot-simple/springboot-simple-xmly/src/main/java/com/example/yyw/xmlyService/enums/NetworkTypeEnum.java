package com.example.yyw.xmlyService.enums;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/10 10:54
 * @describe
 */
public enum NetworkTypeEnum{
    ON_LINE(0, "联网播放"),
    OFF_LINE(1, "断网播放");
    int code;
    String name;

    NetworkTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
