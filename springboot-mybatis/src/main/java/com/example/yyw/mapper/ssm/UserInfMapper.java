package com.example.yyw.mapper.ssm;

import com.example.yyw.model.ssm.UserInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserInfMapper {
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
    int insert(UserInf record);

    /**
     * @describe 动态字段,写入数据库记录
     * @param record
     * @return int
     */
    int insertSelective(UserInf record);

    /**
     * @describe 根据指定主键获取一条数据库记录
     * @param id
     * @return UserInf
     */
    UserInf selectByPrimaryKey(Integer id);

    /**
     * @describe 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(UserInf record);

    /**
     * @describe 根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKey(UserInf record);

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


    /**
     * @description 查询所有数据
     * */
    List<UserInf> findAll();
}