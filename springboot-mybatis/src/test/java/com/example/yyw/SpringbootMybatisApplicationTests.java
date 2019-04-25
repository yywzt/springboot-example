package com.example.yyw;

import com.example.yyw.mapper.ssm.UserInfMapper;
import com.example.yyw.model.ssm.UserInf;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootMybatisApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserInfMapper userInfMapper;
    /**
     * 测试@Select注解
     * */
    @Test
    public void testSelect(){
        String uname = "aaa";
        String passwd = "$2a$10$V1VtKl.2hhczpRY.6n205.KTTxbrgONIBdDbu7C05.lgx.TgVYS7u";
        UserInf userinf1 = userInfMapper.findUserInfByUnameAndPasswd(uname, passwd);
        UserInf userinf2 = userInfMapper.findUserInfByUnameAndPasswd2(uname, passwd);
        log.info("userinf1: {}",userinf1);
        log.info("userinf2: {}",userinf2);
    }
}
