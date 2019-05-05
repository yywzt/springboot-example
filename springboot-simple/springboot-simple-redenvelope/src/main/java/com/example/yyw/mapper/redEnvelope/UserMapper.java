package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.BaseMapper;

import java.math.BigDecimal;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户增加金额
     *
     * @param id
     * @param money 增加的金额
     * @return
     */
    @Update("update user set money = money + #{money} , updation_date = now(), updated_by = #{updatedBy} where id = #{id}")
    int updateMoneyById(Long id, BigDecimal money, String updatedBy);

    @Select("select * from user where user_name = #{userName} and enabled_flag = #{enabledFlag}")
    User selectByUserName(String userName, Long enabledFlag);

}