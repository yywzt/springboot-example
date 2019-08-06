package com.example.yyw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.example.yyw"})
public class SpringbootSimpleXmlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSimpleXmlyApplication.class, args);
    }

}
