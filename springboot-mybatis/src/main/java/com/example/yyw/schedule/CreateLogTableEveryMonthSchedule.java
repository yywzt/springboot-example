package com.example.yyw.schedule;

import com.example.yyw.base.GenericModel;
import com.example.yyw.model.ssm.Log;
import com.example.yyw.service.TableService;
import com.example.yyw.service.ssm.LogService;
import com.example.yyw.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/13 19:40
 * @describe
 */
@Slf4j
//@Component
public class CreateLogTableEveryMonthSchedule {

    private static final String SCHEMANAME = "ssm";
    private static final String TABLENAME = "log";
    public static final String SEPARATOR = "_bak_";
    private static final String APOSTROPHE = "'";
    private static final String COMMA = ",";
    private static final String LEFTBRACKETS = "(";
    private static final String RIGHTBRACKETS = ")";


    @Autowired
    private LogService logService;
    @Autowired
    private TableService tableService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private void alterTableName(String tableName, String newTableName){
        String sql = "ALTER TABLE " + tableName + " RENAME TO " + newTableName;
        int update = jdbcTemplate.update(sql);
        log.info("{}",update);
    }

    private void createTable(String tableName, String lastTableName){
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLENAME + " like " + lastTableName;
        int update = jdbcTemplate.update(sql);
        log.info("{}",update);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Scheduled(cron = "0 0/1 * * * ? ")
    private void createLogTable() {
        String date = TimeUtil.localDateTimeToString(TimeUtil.now(), TimeUtil.DEFAULT_DATE_TIME_FORMATTER2);
        String newTableName = new StringBuffer(TABLENAME).append(SEPARATOR).append(date).toString();
        bakLog(SCHEMANAME, TABLENAME, newTableName);
        log.info("alter table msg : {}",tableService.tableExist(TABLENAME, date));
        if(!tableService.tableExist(TABLENAME)){
            log.error("create table error");
            throw new RuntimeException("异常");
        }
    }

    /**
     * 调用存储过程
     * call bakLog('ssm','log','log_bak')
     * @param tableName
     * @param newTableName
     */
    private void bakLog(String schemaName, String tableName, String newTableName){
        String sql = new StringBuffer("CALL bakLog").append(LEFTBRACKETS)
                .append(APOSTROPHE).append(schemaName).append(APOSTROPHE).append(COMMA)
                .append(APOSTROPHE).append(tableName).append(APOSTROPHE).append(COMMA)
                .append(APOSTROPHE).append(newTableName).append(APOSTROPHE).append(RIGHTBRACKETS).toString();
        jdbcTemplate.execute(sql);
        log.info("bakLog success");
    }

    private static final String GROUP_NAME = "YYW";

    @Scheduled(fixedDelay = 100)
    public void init(){
        Log logs = new Log();
        logs.setGroupName(GROUP_NAME);
        logs.setLogMsg("log msg : " + TimeUtil.localDateTimeToString(TimeUtil.now(),1));
        initCommonData(logs);

        if(logService.getLogMapper().insert(logs) == 1){
            log.info("insert logs success");
        }
    }

    private void initCommonData(GenericModel<Long> model){
        model.setCreatedBy("-1");
        model.setCreationDate(new Timestamp(System.currentTimeMillis()));
    }
}
