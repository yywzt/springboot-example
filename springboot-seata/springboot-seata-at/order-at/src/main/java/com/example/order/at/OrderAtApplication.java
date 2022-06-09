package com.example.order.at;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableAutoDataSourceProxy(dataSourceProxyMode = "XA")
public class OrderAtApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderAtApplication.class, args);
    }

}
