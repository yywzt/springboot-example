package com.example.yyw.service;

import com.example.yyw.constant.Constants;
import com.example.yyw.constant.ResponseData;
import com.example.yyw.dto.RedEnvelopeDto;
import com.example.yyw.enums.EnvelopeEnum;
import com.example.yyw.mapper.redEnvelope.RedEnvelopeMapper;
import com.example.yyw.model.redEnvelope.RedEnvelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/23 17:05
 * @describe
 */
@Service
@Slf4j
public class RedEnvelopeService {

    @Autowired
    private RedEnvelopeMapper redEnvelopeMapper;

    public RedEnvelopeMapper getRedEnvelopeMapper() {
        return redEnvelopeMapper;
    }

    public ResponseData getRedEnvelope(Long id){
        RedEnvelope redEnvelope = redEnvelopeMapper.selectByPrimaryKey(id);
        return ResponseData.success(redEnvelope);
    }

    /**
     * 私发红包
     * */
    public ResponseData sendSingleRedEnvelope(RedEnvelopeDto redEnvelopeDto) {
        if(redEnvelopeDto.getReceiveId() == null){
            return ResponseData.failure("接收者不能为空");
        }
        if(!redEnvelopeDto.getEnvelopeType().equals(EnvelopeEnum.SINGLE.getCode())){
            return ResponseData.failure("不能识别的红包类型");
        }
        RedEnvelope redEnvelope = new RedEnvelope();
        BeanUtils.copyProperties(redEnvelopeDto,redEnvelope);
        log.info("msg: {}",redEnvelope);
        redEnvelope.setCount(1);
        redEnvelope.setRemainCount(redEnvelope.getCount());
        redEnvelope.setCreationDate(new Timestamp(System.currentTimeMillis()));
        redEnvelope.setCreatedBy(Constants.DEFAULTCREATEBY);
        int i = redEnvelopeMapper.insertSelective(redEnvelope);
        return ResponseData.success();
    }

    /**
     * 群发红包
     * */
    public ResponseData sendQunRedEnvelope(RedEnvelopeDto redEnvelopeDto) {
        if(redEnvelopeDto.getQunId() == null){
            return ResponseData.failure("群id不能为空");
        }
        if(redEnvelopeDto.getCount() == null || redEnvelopeDto.getCount() <= 0){
            return ResponseData.failure("数量不合法");
        }
        if(!redEnvelopeDto.getEnvelopeType().equals(EnvelopeEnum.QUN.getCode())){
            return ResponseData.failure("不能识别的红包类型");
        }
        RedEnvelope redEnvelope = new RedEnvelope();
        BeanUtils.copyProperties(redEnvelopeDto,redEnvelope);
        log.info("msg: {}",redEnvelope);
        redEnvelope.setRemainCount(redEnvelope.getCount());
        redEnvelope.setCreationDate(new Timestamp(System.currentTimeMillis()));
        redEnvelope.setCreatedBy(Constants.DEFAULTCREATEBY);
        int i = redEnvelopeMapper.insertSelective(redEnvelope);
        return ResponseData.success(redEnvelopeDto);
    }

    /**
     * 领取红包
     * */
    public ResponseData receivingRedEnvelope(Long id, Long uid) {
        return ResponseData.success();
    }
}
