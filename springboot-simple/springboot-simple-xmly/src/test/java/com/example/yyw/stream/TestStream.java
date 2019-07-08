package com.example.yyw.stream;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/23 17:00
 * @describe
 */
@Slf4j
public class TestStream {

    @Test
    public void test1(){
        List<Long> originIdList = Lists.newArrayList();
        for (int i=1;i<=10;i++) {
            originIdList.add(Long.valueOf(String.valueOf(i)));
        }
        log.info("originIdList: {}",originIdList);

        List<Long> originIdList2 = Lists.newArrayList();
        for (int i=1;i<=10;i+=2) {
            originIdList2.add(Long.valueOf(String.valueOf(i)));
        }
        originIdList2.add(12L);
        originIdList2.add(13L);
        originIdList2.add(14L);
        log.info("originIdList2: {}",originIdList2);

        //
        List<Long> collect = originIdList2.stream().filter(aLong -> originIdList.contains(aLong)).collect(Collectors.toList());
        List<Long> collect2 = originIdList2.stream().filter(aLong -> !originIdList.contains(aLong)).collect(Collectors.toList());
        log.info("collect: {}",collect);
        log.info("collect: {}",collect2);
    }
}
