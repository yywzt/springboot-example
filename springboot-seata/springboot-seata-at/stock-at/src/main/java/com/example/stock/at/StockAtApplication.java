package com.example.stock.at;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoDataSourceProxy(dataSourceProxyMode = "XA")
public class StockAtApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockAtApplication.class, args);
    }

}
