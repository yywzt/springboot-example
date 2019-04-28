package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.RedEnvelope;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface RedEnvelopeMapper extends BaseMapper<RedEnvelope> {

    /**
     * @describe 根据指定主键获取一条数据库记录
     * @param id
     * @param receiveId 接受者id
     * @return RedEnvelope
     */
    RedEnvelope selectByIdAndReceiveIdAndEnvelopeTypeAndEnabledStatus(Long id,Long receiveId,Integer envelopeType,Long enabledFlag);

    int updateRedEnvelopeStatusAndRemainCount(RedEnvelope redEnvelope);

    @Select("SELECT * FROM red_envelope WHERE id = #{id} and envelope_type = #{envelopeType} and enabled_flag = #{enabledFlag} ")
    RedEnvelope selectByIdAndTypeAndEnabledFlag(Long id, Integer envelopeType, Long enabledFlag);
}