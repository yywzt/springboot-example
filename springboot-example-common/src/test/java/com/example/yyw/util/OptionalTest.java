package com.example.yyw.util;

import org.junit.Test;
import sun.security.action.GetPropertyAction;

import java.nio.charset.Charset;
import java.security.AccessController;
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

    @Test
    public void test_2(){
        String encoding = System.getProperty("file.encoding");
        System.out.println("\n当前编码:" + encoding);
    }

    @Test
    public void test_3(){
        Charset defaultCharset = null;
        if (defaultCharset == null) {
            synchronized (Charset.class) {
                String csn = AccessController.doPrivileged(
                        new GetPropertyAction("file.encoding"));
                System.out.println(csn);
            }
        }
        System.out.println(defaultCharset);
    }

}
