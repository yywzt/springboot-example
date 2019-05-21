package com.example.yyw.xmlyService.enums;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/10 10:54
 * @describe
 */
public enum DeviceTypeEnum{
    IOS(1, "IOS系统"),
    ANDROID(2, "Android系统"),
    WEB(3, "WEB"),
    LINUX(4, "Linux系统"),
    ECOS(5, "ecos系统"),
    QNIX(6, "qnix系统");
    int code;
    String name;

    DeviceTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static DeviceTypeEnum findByCode(int code){
        for(DeviceTypeEnum deviceTypeEnum : DeviceTypeEnum.values()){
            if(deviceTypeEnum.getCode() == code){
                return deviceTypeEnum;
            }
        }
        return ANDROID;
    }
}
