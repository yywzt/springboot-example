package com.example.yyw.xmlyService.enums;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/10 10:54
 * @describe
 */
public enum CalcDimensionEnum{
    HOT(1, "最火"),
    NEW(2, "最新"),
    PLAY(3, "最多播放");
    int code;
    String name;

    CalcDimensionEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static CalcDimensionEnum findByCode(int code){
        for(CalcDimensionEnum calcDimensionEnum : CalcDimensionEnum.values()){
            if(code == calcDimensionEnum.getCode()){
                return calcDimensionEnum;
            }
        }
        return HOT;
    }
}
