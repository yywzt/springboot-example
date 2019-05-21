package com.example.yyw.service;

import com.example.yyw.schedule.CreateLogTableEveryMonthSchedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/13 19:47
 * @describe
 */
@Slf4j
@Service
public class TableService {

    private static final String SPLIT_SYMBOL = CreateLogTableEveryMonthSchedule.SEPARATOR;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String tableExist(String table, String date) {
        String tableName = table + SPLIT_SYMBOL + date;
        boolean b = tableExist(tableName);
        if (b) {
            return table + "分表成功;";
        } else {
            return table + "分表失败;";
        }
    }

    public boolean tableExist(String tableName) {
        String sql = "SELECT count(TABLE_NAME) FROM information_schema.`TABLES` WHERE TABLE_SCHEMA = 'ssm' AND TABLE_NAME = '" + tableName + "'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        if (count > 0) {
            log.info("{} IS EXIST",tableName);
            return true;
        } else {
            log.info("{} IS NOT EXIST",tableName);
            return false;
        }
    }

}
