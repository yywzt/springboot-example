package com.example.yyw.xmlyService.enums;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/10 10:54
 * @describe
 */
public enum TagTypeEnum{
    ALBUM_TAG(0, "专辑标签"),
    VOICE_TAG(1, "声音标签");
    int code;
    String name;

    TagTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static TagTypeEnum findByCode(int code){
        for(TagTypeEnum tagTypeEnum : TagTypeEnum.values()){
            if(code == tagTypeEnum.getCode()){
                return tagTypeEnum;
            }
        }
        return ALBUM_TAG;
    }
}
