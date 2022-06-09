package com.example.account.at;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoDataSourceProxy(dataSourceProxyMode = "XA")
public class AccountAtApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountAtApplication.class, args);
    }

}
