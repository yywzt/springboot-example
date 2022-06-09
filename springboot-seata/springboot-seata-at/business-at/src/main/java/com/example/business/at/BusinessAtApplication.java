package com.example.business.at;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableAutoDataSourceProxy(dataSourceProxyMode = "XA")
public class BusinessAtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessAtApplication.class, args);
    }

}
