package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.RedEnvelope;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RedEnvelopeMapper {
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
    int insert(RedEnvelope record);

    /**
     * @describe 动态字段,写入数据库记录
     * @param record
     * @return int
     */
    int insertSelective(RedEnvelope record);

    /**
     * @describe 根据指定主键获取一条数据库记录
     * @param id
     * @return RedEnvelope
     */
    RedEnvelope selectByPrimaryKey(Long id);

    /**
     * @describe 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(RedEnvelope record);

    /**
     * @describe 根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKey(RedEnvelope record);
}