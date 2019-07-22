package com.example.yyw.springbootkafka.topic;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/9 19:18
 * @describe
 */
public enum TopicEnum {

    TEST("test"),
    TEST2("test2");

    private String name;

    TopicEnum(String name) {
        this.name = name;
    }

    public static TopicEnum findByName(String name) {
        for (TopicEnum topicEnum : TopicEnum.values()) {
            if (name.equals(topicEnum.getName())) {
                return topicEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
