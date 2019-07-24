package com.example.yyw.abstract_factory.enums;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/24 17:36
 * @describe
 */
public enum SkinFactoryTypeEnum {

    SPRING("spring"),
    SUMMER("summer");

    private String name;

    SkinFactoryTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
