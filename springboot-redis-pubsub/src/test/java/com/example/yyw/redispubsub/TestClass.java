package com.example.yyw.redispubsub;

import org.junit.Test;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/1/18 10:13
 * @desc
 */
public class TestClass {

    @Test
    public void test(){
        String s = "Website:www.yiibai.com";
        char buf[]=new char[30];
        s.getChars(12,18,buf,10);
        System.out.println(buf);
    }

    @Test
    public void testSet(){
        HashSet<String> set = new HashSet<>();
        set.add("a");
        set.add("a");
        set.add("b1");
        set.add("b2");
        set.add("b2");
        System.out.println(set);//[b2, a, b1]
    }
    @Test
    public void testZSet(){
        TreeSet<Object> treeSet = new TreeSet<>();
        treeSet.add("a");
        treeSet.add("a");
        treeSet.add("b1");
        treeSet.add("b2");
        treeSet.add("b2");
        System.out.println(treeSet);//[a, b1, b2]
    }

}
