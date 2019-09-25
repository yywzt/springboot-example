package com.example.yyw.util;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/4 11:11 布隆过滤器
 */
public class BloomFilterTest {

    @Test
    public void test_1(){
        BloomFilter<Long> longBloomFilter = BloomFilter.create(Funnels.longFunnel(), 1);
        for(int i=0;i<10;i++) {
            longBloomFilter.put((long)i);
        }
        System.out.println(longBloomFilter);
        System.out.println(longBloomFilter.mightContain(1L));
        System.out.println(longBloomFilter.mightContain(10L));
    }
}
