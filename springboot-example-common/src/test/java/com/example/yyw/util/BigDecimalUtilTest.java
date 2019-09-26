package com.example.yyw.util;

import org.junit.Test;


/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/23 19:29
 */
public class BigDecimalUtilTest {

    @Test
    public void test_1(){
        Double d1 = 5.3D;
        Double d2 = 3.2D;
        System.out.println(d1-d2);
        System.out.println(BigDecimalUtil.subtract(d1,d2));
    }
}