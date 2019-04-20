package com.example.yyw.mapper.ssm;

import com.example.yyw.model.ssm.UserRoles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRolesMapper {
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
    int insert(UserRoles record);

    /**
     * @describe 动态字段,写入数据库记录
     * @param record
     * @return int
     */
    int insertSelective(UserRoles record);

    /**
     * @describe 根据指定主键获取一条数据库记录
     * @param id
     * @return UserRoles
     */
    UserRoles selectByPrimaryKey(Integer id);

    /**
     * @describe 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(UserRoles record);

    /**
     * @describe 根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKey(UserRoles record);
}