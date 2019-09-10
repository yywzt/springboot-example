package com.example.yyw;

import com.example.yyw.autowired.AutowiredToMap;
import com.example.yyw.autowired.BeanInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTestApplicationTests {

    @Autowired
    private AutowiredToMap autowiredToMap;

    @Test
    public void contextLoads() {
        Map<String, BeanInterface> map = autowiredToMap.getMap();
        log.info("map: {}", map);
    }

}
