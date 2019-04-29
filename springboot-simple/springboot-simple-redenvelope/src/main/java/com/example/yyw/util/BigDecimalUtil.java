package com.example.yyw.util;

import java.math.BigDecimal;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/23 17:50
 * @describe
 */
public class BigDecimalUtil {

    public static BigDecimal add(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        return bigDecimal1.add(bigDecimal2);
    }

    /**
     * 加法
     */
    public static BigDecimal add(Double v1, Double v2) {
        BigDecimal bigDecimal = BigDecimal.valueOf(v1);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(v2);
        return bigDecimal.add(bigDecimal1);
    }

    /**
     * 减法
     */
    public static BigDecimal subtract(Double v1, Double v2) {
        BigDecimal bigDecimal = BigDecimal.valueOf(v1);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(v2);
        return bigDecimal.subtract(bigDecimal1);
    }

    /**
     * 乘法
     */
    public static BigDecimal multiply(Double v1, Double v2) {
        BigDecimal bigDecimal = BigDecimal.valueOf(v1);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(v2);
        return bigDecimal.multiply(bigDecimal1);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(Double v1, Double v2) {
        BigDecimal bigDecimal = BigDecimal.valueOf(v1);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(v2);
        return bigDecimal.divide(bigDecimal1);
    }

    public static void main(String[] args) {
        Double d1 = new Double(99d);
        Double d2 = new Double(3.3d);

        System.out.println("add: " + add(d1, d2));
        System.out.println("subtract: " + subtract(d1, d2));
        System.out.println("divide: " + divide(d1, d2));
        System.out.println("multiply: " + multiply(d1, d2));
    }

}
