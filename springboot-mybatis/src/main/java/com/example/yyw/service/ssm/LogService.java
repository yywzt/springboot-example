package com.example.yyw.service.ssm;

import com.example.yyw.mapper.ssm.BasedMapper;
import com.example.yyw.mapper.ssm.LogMapper;
import com.example.yyw.model.ssm.Log;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/13 19:48
 * @describe
 */
@Slf4j
@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private BasedMapper basedMapper;

    public LogMapper getLogMapper() {
        return logMapper;
    }

    public void createTable(){
        logMapper.createTable();
    }

    /**
     * 重命名表
     * @param originalTableName 原表名
     * @param newTableName 新表名
     */
    public void alterTableName(String originalTableName, String newTableName){
        logMapper.alterTableName(originalTableName,newTableName);
    }

    @Transactional
    public void saveLogs(String tableName){
        //保存数据
        Log logModel = new Log();
        logModel.setGroupName("yyw");
        logModel.setLogMsg("hahaha01");
        basedMapper.save(tableName,logModel);
    }

    @Transactional
    public void batchSaveLogs(String tableName){
        List<Log> logs = Lists.newArrayList();
        //保存数据
        for(int i=0;i<10;i++) {
            Log logModel = new Log();
            logModel.setGroupName("yyw");
            logModel.setLogMsg("hahaha0" + i);
            logs.add(logModel);
        }
        int i = basedMapper.batchSave(tableName, logs);
        i = i/0;
        log.info("{}",i);
    }
}
