package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

@Mapper
public interface UserMapper {
    /**
     * @param id
     * @return int
     * @describe 根据主键删除数据库的记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @param record
     * @return int
     * @describe 新写入数据库记录
     */
    int insert(User record);

    /**
     * @param record
     * @return int
     * @describe 动态字段, 写入数据库记录
     */
    int insertSelective(User record);

    /**
     * @param id
     * @return User
     * @describe 根据指定主键获取一条数据库记录
     */
    User selectByPrimaryKey(Long id);

    /**
     * @param record
     * @return int
     * @describe 动态字段, 根据主键来更新符合条件的数据库记录
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * @param record
     * @return int
     * @describe 根据主键来更新符合条件的数据库记录
     */
    int updateByPrimaryKey(User record);

    /**
     * 用户增加金额
     *
     * @param id
     * @param money 增加的金额
     * @return
     */
    @Update("update user set money = money + #{money} , updation_date = now(), updated_by = #{updatedBy} where id = #{id}")
    int updateMoneyById(Long id, BigDecimal money,String updatedBy);

    @Select("select * from user where user_name = #{userName} and enabled_flag = #{enabledFlag}")
    User selectByUserName(String userName, Long enabledFlag);

}