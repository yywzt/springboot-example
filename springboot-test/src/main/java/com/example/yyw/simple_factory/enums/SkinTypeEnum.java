package com.example.yyw.simple_factory.enums;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/24 16:28
 * @describe
 */
public enum SkinTypeEnum {

    SPRING_BUTTON("spring_button"),
    SUMMER_BUTTON("summer_button"),
    SPRING_TEXTFIELD("spring_textfield"),
    SUMMER_TEXTFIELD("summer_textfield"),
    SPRING_COMBOBOX("spring_combobox"),
    SUMMER_COMBOBOX("summer_combobox");

    private String name;

    SkinTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
