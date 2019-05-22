package com.example.yyw.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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

//多数据源配置
/*@Configuration
@MapperScan(basePackages = "com.example.mapper.ssm", sqlSessionTemplateRef  = "ssmSqlSessionTemplate")
public class SsmDataSourceConfig {

    @Bean(name = "ssmDataSource",initMethod = "init",destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.ssm")
    public DataSource ssmDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "ssmSqlSessionFactory")
    public SqlSessionFactory ssmSqlSessionFactory(@Qualifier("ssmDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/example/mapper/ssm/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "ssmDataSourceTransactionManager")
    public DataSourceTransactionManager ssmTransactionManager(@Qualifier("ssmDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ssmSqlSessionTemplate")
    public SqlSessionTemplate ssmSqlSessionTemplate(@Qualifier("ssmSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}*/
