package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.Qun;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QunMapper {
    /**
     * @describe 根据主键删除数据库的记录
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @describe 新写入数据库记录
     * @param record
     * @return int
     */
    int insert(Qun record);

    /**
     * @describe 动态字段,写入数据库记录
     * @param record
     * @return int
     */
    int insertSelective(Qun record);

    /**
     * @describe 根据指定主键获取一条数据库记录
     * @param id
     * @return Qun
     */
    Qun selectByPrimaryKey(Long id);

    /**
     * @describe 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Qun record);

    /**
     * @describe 根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Qun record);
}