package com.example.yyw.config;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/10 16:46
 * @describe
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 注入主键生成器
     */
    @Bean
    public IKeyGenerator keyGenerator(){
        return new OracleKeyGenerator();
    }
}
