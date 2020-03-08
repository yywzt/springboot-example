package com.example.yyw.util;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2020/2/2 21:24
 * @describe
 */
public class PreconditionsTest {

    @Test
    public void test1(){
        Integer integer = new Integer(1);
        Preconditions.checkNotNull(integer);

        integer = null;
        Preconditions.checkNotNull(integer);
    }
}
