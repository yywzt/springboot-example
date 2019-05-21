package com.example.yyw.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/14 23:55
 * @describe
 */
@Configuration
public class SsmDataSourceConfig {

//    @Bean(name = "ssmDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.ssm")
    public DataSource primaryDataSource() {
        DataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "student")
    public Student student(){
        Student student = new Student();
        return student;
    }

}
