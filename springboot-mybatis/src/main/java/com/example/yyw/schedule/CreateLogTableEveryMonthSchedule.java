package com.example.yyw.schedule;

import com.example.yyw.service.TableService;
import com.example.yyw.service.ssm.LogService;
import com.example.yyw.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/13 19:40
 * @describe
 */
@Slf4j
@Component
public class CreateLogTableEveryMonthSchedule {

    private static final String TABLENAME = "log";
    private static final String SEPARATOR = "_";

    @Autowired
    private LogService logService;
    @Autowired
    private TableService tableService;

    @Transactional(rollbackFor = RuntimeException.class)
    @Scheduled(fixedDelay = 5000)
    private void createLogTable() {
        String date = TimeUtil.localDateTimeToString(TimeUtil.now(), TimeUtil.DEFAULT_DATE_TIME_FORMATTER2);
        log.info("date : {}", date);
        String newTableName = new StringBuffer(TABLENAME).append(SEPARATOR).append(date).toString();
        logService.alterTableName(TABLENAME, newTableName);
        log.info("alter table success : {}", newTableName);
        logService.createTable();
        log.info("create table end : {}", tableService.tableExist(TABLENAME, date));
        throw new RuntimeException("未知异常");
    }
}
