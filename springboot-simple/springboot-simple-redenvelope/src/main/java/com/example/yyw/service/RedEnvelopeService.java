package com.example.yyw.service;

import com.example.yyw.constant.Constants;
import com.example.yyw.constant.ResponseData;
import com.example.yyw.dto.RedEnvelopeDto;
import com.example.yyw.enums.EnvelopeEnum;
import com.example.yyw.enums.EnvelopeStatusEnum;
import com.example.yyw.exception.DefaultException;
import com.example.yyw.mapper.redEnvelope.RedEnvelopeDetailMapper;
import com.example.yyw.mapper.redEnvelope.RedEnvelopeMapper;
import com.example.yyw.mapper.redEnvelope.UserMapper;
import com.example.yyw.model.redEnvelope.RedEnvelope;
import com.example.yyw.model.redEnvelope.RedEnvelopeDetail;
import com.example.yyw.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private RedEnvelopeDetailMapper redEnvelopeDetailMapper;
    @Autowired
    private UserMapper userMapper;

    public RedEnvelopeMapper getRedEnvelopeMapper() {
        return redEnvelopeMapper;
    }

    public RedEnvelopeDetailMapper getRedEnvelopeDetailMapper() {
        return redEnvelopeDetailMapper;
    }

    /**
     * 红包详情
     * @param id
     * @return
     */
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
        redEnvelope.setCreationDate(DateUtil.getNowTimestamp());
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
        redEnvelope.setCreationDate(DateUtil.getNowTimestamp());
        redEnvelope.setCreatedBy(Constants.DEFAULTCREATEBY);
        if(redEnvelopeMapper.insertSelective(redEnvelope) >= 1){
            return ResponseData.success(redEnvelopeDto);
        }
        return ResponseData.failure();
    }

    /**
     * 领取私发红包
     * */
    @Transactional(rollbackFor = DefaultException.class)
    public ResponseData receivingSingleRedEnvelope(Long id, Long uid) {
        RedEnvelopeDetail envelopeDetail = redEnvelopeDetailMapper.selectByEnvelopeIdAndReceiveId(id, uid,Constants.ENABLEDFLAG);
        if(envelopeDetail != null){
            return ResponseData.failure(Constants.RECEIVED);
        }
        RedEnvelope redEnvelope = redEnvelopeMapper.selectByIdAndReceiveIdAndEnvelopeTypeAndEnabledStatus(id,uid,EnvelopeEnum.SINGLE.getCode(),Constants.ENABLEDFLAG);
        String msg = "";
        //存在且是私发红包
        if(redEnvelope != null){
            if(redEnvelope.getStatus().equals(EnvelopeStatusEnum.AVAILABLE.getCode())){
                //领取详情model
                RedEnvelopeDetail redEnvelopeDetail = new RedEnvelopeDetail();
                redEnvelopeDetail.setRedEnvelopeId(redEnvelope.getId());
                redEnvelopeDetail.setReceiveId(uid);
                redEnvelopeDetail.setReceiveDate(DateUtil.getNowTimestamp());
                redEnvelopeDetail.setReceiveMoney(redEnvelope.getAmount());
                redEnvelopeDetail.setCreationDate(DateUtil.getNowTimestamp());
                redEnvelopeDetail.setCreatedBy(Constants.DEFAULTCREATEBY);
                if(redEnvelopeDetailMapper.insertSelective(redEnvelopeDetail) <= 0){
                    throw new DefaultException("领取失败");
                }
                //更新红包状态
                redEnvelope.setStatus(EnvelopeStatusEnum.FINISHED.getCode());
                redEnvelope.setRemainCount(redEnvelope.getRemainCount() - 1);
                redEnvelope.setUpdationDate(DateUtil.getNowTimestamp());
                redEnvelope.setUpdatedBy(Constants.DEFAULTUPDATEBY);
                if(redEnvelopeMapper.updateRedEnvelopeStatusAndRemainCount(redEnvelope) <= 0){
                    throw new DefaultException("领取失败");
                }
                //用户金额增加
                if(userMapper.updateMoneyById(uid,redEnvelope.getAmount(),Constants.DEFAULTUPDATEBY) <= 0){
                    throw new DefaultException("领取失败");
                }
                return ResponseData.success(redEnvelopeDetail);
            }
        }
        return ResponseData.failure(redEnvelope == null?"红包不存在的":redEnvelope.getEnvelopeStatusName());
    }

    /**
     * 领取私发红包
     * */
    public ResponseData receivingQunRedEnvelope(Long id, Long uid) {
        return ResponseData.success();
    }
}
