package com.example.yyw.util;

import org.junit.Test;

import java.util.Optional;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/19 8:52
 */
public class OptionalTest {

    @Test
    public void test_optional(){
        String s = "s";
        Optional.ofNullable(s).orElse(print("orElse"));
        Optional.ofNullable(s).orElseGet(() -> print("orElseGet"));
    }

    public String print(String name){
        System.out.println(name);
        return name;
    }
}
