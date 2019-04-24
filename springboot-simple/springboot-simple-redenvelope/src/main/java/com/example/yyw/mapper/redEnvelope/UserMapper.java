package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface UserMapper {
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
    int insert(User record);

    /**
     * @describe 动态字段,写入数据库记录
     * @param record
     * @return int
     */
    int insertSelective(User record);

    /**
     * @describe 根据指定主键获取一条数据库记录
     * @param id
     * @return User
     */
    User selectByPrimaryKey(Long id);

    /**
     * @describe 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @describe 根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKey(User record);

    /**
     * 用户增加金额
     * @param id
     * @param money 增加的金额
     * @return
     */
    int updateMoneyById(@Param("id") Long id, @Param("money") BigDecimal money,@Param("updatedBy") String updatedBy);

    User selectByUserName(@Param("userName") String userName, @Param("enabledFlag") Long enabledFlag);
}