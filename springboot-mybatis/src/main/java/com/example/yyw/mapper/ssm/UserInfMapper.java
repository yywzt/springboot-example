package com.example.yyw.mapper.ssm;

import com.example.yyw.model.ssm.UserInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface UserInfMapper extends BaseMapper<UserInf> {

    @Select("select * from user_inf where uname = #{uname} and passwd = #{passwd}")
    UserInf findUserInfByUnameAndPasswd(@Param("uname") String uname, @Param("passwd") String passwd);

    @Select("select * from user_inf where uname = #{uname} and passwd = #{passwd}")
    UserInf findUserInfByUnameAndPasswd2(String uname, String passwd);



    /**
     * @description 根据指定的uname查询用户
     * @author rico
     * @created 2017年6月12日 下午12:19:24
     * @param uname
     * @return
     */
    UserInf findUserInfByUname(@Param("uname") String uname);

}