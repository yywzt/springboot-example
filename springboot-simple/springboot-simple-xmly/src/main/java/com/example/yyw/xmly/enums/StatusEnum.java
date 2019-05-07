package com.example.yyw.xmly.enums;

/**
 * 通用型的状态枚举类型，原则上只能添加新的枚举值，已有枚举值不可修改。
 *
 * @author yanzhitao@xiaomalixing.com
 * @date 2019-05-07
 **/
public enum StatusEnum {
    DEFAULT(0, "默认值"),

    SUCCESS(1, "成功"),

    FAILED(-1, "失败"),

    HISTORY(-2, "归档记录");

    StatusEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    private int code;
    private String name;

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static StatusEnum findByCode(int code){
        for(StatusEnum statusEnum : StatusEnum.values()){
            if(statusEnum.getCode() == code){
                return statusEnum;
            }
        }
        return DEFAULT;
    }
}
