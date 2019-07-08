package com.example.yyw.jpa;

import com.example.yyw.jpa.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    public void contextLoads() {
        studentService.getAll();
    }

}
