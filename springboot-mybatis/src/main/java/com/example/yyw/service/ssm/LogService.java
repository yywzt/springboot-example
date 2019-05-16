package com.example.yyw.service.ssm;

import com.example.yyw.mapper.ssm.LogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
