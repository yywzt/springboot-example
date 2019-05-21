package com.example.yyw.mapper.ssm;

import com.example.yyw.model.ssm.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/20 16:16
 * @describe
 */
@Mapper
public interface BasedMapper {

    /**
     * 重命名表
     * @param sourceTableName
     * @param newTableName
     */
    @Update("alter table ${sourceTableName} rename ${newTableName}")
    void alterTableName(@Param("sourceTableName") String sourceTableName, @Param("newTableName") String newTableName);

    /**
     * 删除表
     * @param tableName
     */
    @Update("drop table IF EXISTS ${tableName}")
    void dropTable(@Param("tableName") String tableName);

    /**
     * 创建表
     */
    @Update("CREATE TABLE IF NOT EXISTS log (\n" +
            "          `id` bigint(10) NOT NULL AUTO_INCREMENT,\n" +
            "          `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
            "          `creation_date` datetime DEFAULT NULL,\n" +
            "          `enabled_flag` bigint(20) DEFAULT NULL,\n" +
            "          `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
            "          `updation_date` datetime DEFAULT NULL,\n" +
            "          `group_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
            "          `log_msg` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
            "          PRIMARY KEY (`id`)\n" +
            "        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci")
    void createTable1();

    @Update("CREATE TABLE IF NOT EXISTS ${newTableName} LIKE ${sourceTableName}")
    void createTable(@Param("newTableName") String newTableName, @Param("sourceTableName") String sourceTableName);

    @Update("INSERT INTO `ssm`.${tableName} (`created_by`, `creation_date`, `enabled_flag`, `updated_by`, `updation_date`, `group_name`, `log_msg`) " +
            "VALUES (#{log.createdBy}, #{log.creationDate}, #{log.enabledFlag}, #{log.updatedBy}, #{log.updationDate}, #{log.groupName}, #{log.logMsg})")
    int save(@Param("tableName") String tableName, Log log);

    int batchSave(@Param("tableName") String tableName, @Param("logs") List<Log> logs);
}
