package com.example.yyw.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/27 19:14
 * @describe 代码提炼快捷键 Ctrl+shift+Alt+T , alt+shift+m
 */
public class FizzBuzzTest {

    @Test
    public void test_fizz_buzz_3(){
        FizzBuzz fizzBuzz = new FizzBuzz();
        Assert.assertEquals(getExpected(fizzBuzz, 1),"1");
        Assert.assertEquals(getExpected(fizzBuzz, 3),"fizz");
        Assert.assertEquals(getExpected(fizzBuzz, 5),"buzz");
        Assert.assertEquals(getExpected(fizzBuzz, 15),"fizzbuzz");
    }

    private String getExpected(FizzBuzz fizzBuzz, int i) {
        return fizzBuzz.pushNumber(i);
    }

}