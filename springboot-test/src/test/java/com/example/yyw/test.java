package com.example.yyw;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/22 18:36
 * @describe
 */
public class test {

    @Test
    public void test_1(){
        Map<Boolean, String> map = new HashMap<Boolean, String>(){{
            put(true, "true");
            put(false, "false");
        }};
        System.out.println(map);
        System.out.println(map.get(true));
        System.out.println(map.get(false));
        System.out.println(map.get(Boolean.TRUE));
        System.out.println(map.get(Boolean.FALSE));
    }
}
