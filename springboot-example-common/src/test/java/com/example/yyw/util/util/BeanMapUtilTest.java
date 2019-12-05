package com.example.yyw.util.util;

import com.example.yyw.util.BeanMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/12/5 10:15
 * @description
 */
@Slf4j
public class BeanMapUtilTest {

    @Test
    public void test_1() {
        Student student = new Student("1","2","3","4","5");
        Map map = BeanMapUtil.beanConvertToMap(student);
        log.info("result: {}", map);
    }

}
