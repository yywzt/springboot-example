package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.Qun;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface QunMapper extends BaseMapper<Qun> {

    @Select("select * from qun where qun_name = #{qunName} and enabled_flag = #{enabledFlag}")
    Qun selectByQunName(String qunName, Long enabledFlag);
}