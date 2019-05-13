package com.example.yyw.xmlyService.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/10 10:54
 * @describe
 */
public enum SortEnum{
    ASC("asc", "喜马拉雅正序"),
    DESC("desc", "喜马拉雅倒序"),
    TIME_ASC("time_asc", "发布时间升序"),
    TIME_DESC("time_desc", "发布时间降序");
    String code;
    String name;

    SortEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static SortEnum getByCode(String code){
        for(SortEnum sortEnum : SortEnum.values()){
            if(StringUtils.equalsIgnoreCase(sortEnum.getCode(), code)){
                return sortEnum;
            }
        }
        return ASC;
    }
}
