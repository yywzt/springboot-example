package com.example.yyw.md5;

import com.example.yyw.util.EncodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.DigestUtils;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/5 16:18
 * @describe
 */
@Slf4j
public class MD5UtilTest {

    @Test
    public void test1(){
        String str = "123456";
        String s = EncodeUtil.MD5(str);
        String s1 = DigestUtils.md5DigestAsHex(str.getBytes());
        log.info(s);
        log.info(s1);
    }

}
