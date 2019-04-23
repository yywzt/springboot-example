package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.RedEnvelopeDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RedEnvelopeDetailMapper {
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
    int insert(RedEnvelopeDetail record);

    /**
     * @describe 动态字段,写入数据库记录
     * @param record
     * @return int
     */
    int insertSelective(RedEnvelopeDetail record);

    /**
     * @describe 根据指定主键获取一条数据库记录
     * @param id
     * @return RedEnvelopeDetail
     */
    RedEnvelopeDetail selectByPrimaryKey(Long id);

    /**
     * @describe 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(RedEnvelopeDetail record);

    /**
     * @describe 根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKey(RedEnvelopeDetail record);
}