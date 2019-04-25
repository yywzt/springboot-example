package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.UserQun;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserQunMapper {
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
    int insert(UserQun record);

    /**
     * @describe 动态字段,写入数据库记录
     * @param record
     * @return int
     */
    int insertSelective(UserQun record);

    /**
     * @describe 根据指定主键获取一条数据库记录
     * @param id
     * @return UserQun
     */
    UserQun selectByPrimaryKey(Long id);

    /**
     * @describe 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(UserQun record);

    /**
     * @describe 根据主键来更新符合条件的数据库记录
     * @param record
     * @return int
     */
    int updateByPrimaryKey(UserQun record);

    @Select("SELECT * from user_qun WHERE qun_id=#{id} and user_id=#{uid} and enabled_flag=#{enabledFlag}")
    UserQun selectByQunIdAndUserId(Long id, Long uid,Long enabledFlag);

    @Update("UPDATE user_qun SET enabled_flag = #{disEnabledflag},updation_date = now(),updated_by = #{defaultupdateby} WHERE qun_id = #{qunId} and user_id= #{userId} and enabled_flag = #{enabledFlag} ")
    int quitQun(Long qunId, Long userId, Long disEnabledflag, Long enabledFlag, String defaultupdateby);
}