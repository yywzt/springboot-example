package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.UserQun;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface UserQunMapper extends BaseMapper<UserQun> {

    @Select("SELECT * from user_qun WHERE qun_id=#{id} and user_id=#{uid} and enabled_flag=#{enabledFlag}")
    UserQun selectByQunIdAndUserId(Long id, Long uid, Long enabledFlag);

    @Update("UPDATE user_qun SET enabled_flag = #{disEnabledflag},updation_date = now(),updated_by = #{defaultupdateby} WHERE qun_id = #{qunId} and user_id= #{userId} and enabled_flag = #{enabledFlag} ")
    int quitQun(Long qunId, Long userId, Long disEnabledflag, Long enabledFlag, String defaultupdateby);
}