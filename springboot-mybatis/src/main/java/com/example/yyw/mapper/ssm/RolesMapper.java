package com.example.yyw.mapper.ssm;

import com.example.yyw.model.ssm.Roles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolesMapper {
    /**
     * @describe 根据主键删除数据库的记录
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @describe 新写入数据库记录
     * @param record
     * @return int
     */
    int insert(Roles record);

    /**
     * @describe 动态字段,写入数据库记录
     * @param record
     * @return int
     */
    int insertSelective(Roles record);

    /**
     * @describe 根据指定主键获取一条数据库记录
     * @param id
     * @return Roles
     */
    Roles selectByPrimaryKey(Integer id);

    /**
     * @describe 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Roles record);

    /**
     * @describe 根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Roles record);
}