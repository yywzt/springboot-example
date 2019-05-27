package com.example.yyw.fizzbuzz;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/27 19:29
 * @describe
 */
public class FizzBuzz {

    public String pushNumber(int i) {
        String x = getFizzBuzzString(i);
        if (x != null) return x;
        return String.valueOf(i);
    }

    private String getFizzBuzzString(int i) {
        if (i % 3 == 0 && i % 5 == 0){
            return "fizzbuzz";
        }else if (i % 3 == 0) {
            return "fizz";
        }else if (i % 5 == 0) {
            return "buzz";
        }else {
            return null;
        }
    }
}
