package com.example.yyw.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/13 19:47
 * @describe
 */
@Slf4j
@Service
public class TableService {

    private static final String SPLIT_SYMBOL = "_";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String tableExist(String table, String date) {
        String sql = "SELECT count(TABLE_NAME) FROM information_schema.`TABLES` WHERE TABLE_SCHEMA = 'ssm' AND TABLE_NAME = '" + table + SPLIT_SYMBOL + date + "'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        if (count > 0) {
            log.info(table + SPLIT_SYMBOL + date + " IS EXIST");
            return table + "分表成功;";
        } else {
            log.info(table + SPLIT_SYMBOL + date + " IS NOT EXIST");
            return table + "分表失败;";
        }
    }
}
