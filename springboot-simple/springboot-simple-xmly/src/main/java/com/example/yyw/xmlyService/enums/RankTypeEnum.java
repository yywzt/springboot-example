package com.example.yyw.xmlyService.enums;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/10 10:54
 * @describe
 */
public enum RankTypeEnum{
    PROGRAMME(1, "节目榜单");
    int code;
    String name;

    RankTypeEnum(int code, String name){
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
