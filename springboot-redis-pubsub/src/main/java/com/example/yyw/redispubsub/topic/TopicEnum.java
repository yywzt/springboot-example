package com.example.yyw.redispubsub.topic;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/1/10 10:19
 * @desc
 */
public enum TopicEnum {

    test1("test1"),
    test2("test2");

    TopicEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
