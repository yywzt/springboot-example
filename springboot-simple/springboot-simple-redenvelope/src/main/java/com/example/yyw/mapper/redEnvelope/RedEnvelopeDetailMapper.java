package com.example.yyw.mapper.redEnvelope;

import com.example.yyw.model.redEnvelope.RedEnvelopeDetail;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface RedEnvelopeDetailMapper extends BaseMapper<RedEnvelopeDetail> {

    RedEnvelopeDetail selectByEnvelopeIdAndReceiveId(Long redEnvelopeId, Long receiveId, Long enabledFlag);
}