package com.example.yyw.mapper.ssm;

import com.example.yyw.model.ssm.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/13 19:36
 * @describe
 */
@Mapper
public interface LogMapper extends BaseMapper<Log>, MySqlMapper<Log>, ConditionMapper<Log> {

    /**
     * 重命名表
     * @param originalTableName
     * @param newTableName
     */
    void alterTableName(@Param("originalTableName") String originalTableName, @Param("newTableName") String newTableName);

    /**
     * 创建表
     */
    void createTable();
}
