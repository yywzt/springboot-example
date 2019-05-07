package com.example.yyw.service.redEnvelope;

import com.example.yyw.constant.Constants;
import com.example.yyw.constant.RedEnvelopeConstants;
import com.example.yyw.constant.RedisConstants;
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
import com.example.yyw.model.redEnvelope.User;
import com.example.yyw.service.GenericService;
import com.example.yyw.util.RedissonUtils;
import com.example.yyw.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/23 17:05
 * @describe
 */
@Service
@Slf4j
public class RedEnvelopeService extends GenericService<Long> {

    @Autowired
    private RedEnvelopeMapper redEnvelopeMapper;
    @Autowired
    private RedEnvelopeDetailMapper redEnvelopeDetailMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private QunService qunService;

    public RedEnvelopeMapper getRedEnvelopeMapper() {
        return redEnvelopeMapper;
    }

    public RedEnvelopeDetailMapper getRedEnvelopeDetailMapper() {
        return redEnvelopeDetailMapper;
    }

    RedissonUtils redissonUtils = RedissonUtils.getInstance();

    /**
     * 根据用户id获取所有红包
     *
     * @return
     */
    public ResponseData getAllRedEnvelope() {
        return null;
    }

    /**
     * 红包详情
     *
     * @param id
     * @return
     */
    public ResponseData getRedEnvelope(Long id) {
        RedEnvelope redEnvelope = redEnvelopeMapper.selectByPrimaryKey(id);
        return ResponseData.success(redEnvelope);
    }

    /**
     * 私发红包
     */
    @Transactional(rollbackFor = DefaultException.class)
    public ResponseData sendSingleRedEnvelope(RedEnvelopeDto redEnvelopeDto) {
        if (redEnvelopeDto.getReceiveId() == null) {
            return ResponseData.failure(RedEnvelopeConstants.RECEIVE_ID_NOTNULL);
        }
        if (!redEnvelopeDto.getEnvelopeType().equals(EnvelopeEnum.SINGLE.getCode())) {
            return ResponseData.failure(RedEnvelopeConstants.RED_ENVELOPE_TYPE_ERROR);
        }
        if (redEnvelopeDto.getSendId().equals(redEnvelopeDto.getReceiveId())) {
            return ResponseData.failure(RedEnvelopeConstants.NOT_SENDID_SAME_RECEIVEID);
        }
        ResponseData responseData = userService.checkUser(redEnvelopeDto.getSendId());
        if (!responseData.isSuccess()) {
            return responseData;
        }
        ResponseData responseData1 = userService.checkUser(redEnvelopeDto.getReceiveId());
        if (!responseData1.isSuccess()) {
            return responseData1;
        }
        //校验余额
        User user = (User) responseData.getData();
        if (user.getMoney().compareTo(redEnvelopeDto.getAmount()) < 0) {
            return ResponseData.failure(RedEnvelopeConstants.MONEY_INSUFFICIENT);
        }
        //减余额
        if (userMapper.updateMoneyById(user.getId(), redEnvelopeDto.getAmount().negate(), Constants.DEFAULTUPDATEBY) <= 0) {
            throw new DefaultException(RedEnvelopeConstants.RED_ENVELOPE_SEND_FAILURE);
        }
        //红包记录
        RedEnvelope redEnvelope = new RedEnvelope();
        BeanUtils.copyProperties(redEnvelopeDto, redEnvelope);
        log.info("msg: {}", redEnvelope);
        redEnvelope.setCount(RedEnvelopeConstants.ONE_SINGLE_RED_ENVE_LOPE_COUNT);
        redEnvelope.setRemainCount(redEnvelope.getCount());
        redEnvelope.setSendDate(TimeUtil.now());
        initBaseData(redEnvelope, Constants.ISNOTUPDATE);
        if (redEnvelopeMapper.insertSelective(redEnvelope) <= 0) {
            throw new DefaultException(RedEnvelopeConstants.RED_ENVELOPE_SEND_FAILURE);
        }
        return ResponseData.success(redEnvelopeDto);
    }

    /**
     * 群发红包
     */
    @Transactional(rollbackFor = DefaultException.class)
    public ResponseData sendQunRedEnvelope(RedEnvelopeDto redEnvelopeDto) {
        if (redEnvelopeDto.getQunId() == null) {
            return ResponseData.failure(RedEnvelopeConstants.QUN_ID_NOTNULL);
        }
        if (redEnvelopeDto.getCount() == null || redEnvelopeDto.getCount() <= 0) {
            return ResponseData.failure(RedEnvelopeConstants.RED_ENVELOPE_COUNT_ERROR);
        }
        if (!redEnvelopeDto.getEnvelopeType().equals(EnvelopeEnum.QUN.getCode())) {
            return ResponseData.failure(RedEnvelopeConstants.RED_ENVELOPE_TYPE_ERROR);
        }

        ResponseData responseData = userService.checkUser(redEnvelopeDto.getSendId());
        if (!responseData.isSuccess()) {
            return responseData;
        }
        ResponseData responseData1 = qunService.checkQun(redEnvelopeDto.getQunId());
        if (!responseData1.isSuccess()) {
            return responseData1;
        }

        //红包总金额
        BigDecimal sumMoney = redEnvelopeDto.getAmount().multiply(new BigDecimal(redEnvelopeDto.getCount()));
        //校验余额
        User user = (User) responseData.getData();
        if (user.getMoney().compareTo(sumMoney) < 0) {
            return ResponseData.failure(RedEnvelopeConstants.MONEY_INSUFFICIENT);
        }
        //减余额
        if (userMapper.updateMoneyById(user.getId(), sumMoney.negate(), Constants.DEFAULTUPDATEBY) <= 0) {
            throw new DefaultException(RedEnvelopeConstants.RED_ENVELOPE_SEND_FAILURE);
        }

        RedEnvelope redEnvelope = new RedEnvelope();
        BeanUtils.copyProperties(redEnvelopeDto, redEnvelope);
        log.info("msg: {}", redEnvelope);
        redEnvelope.setRemainCount(redEnvelope.getCount());
        redEnvelope.setSendDate(TimeUtil.now());
        initBaseData(redEnvelope, Constants.ISNOTUPDATE);
        if (redEnvelopeMapper.insertSelective(redEnvelope) <= 0) {
            throw new DefaultException(RedEnvelopeConstants.RED_ENVELOPE_SEND_FAILURE);
        }
        return ResponseData.success(redEnvelopeDto);
    }

    /**
     * 领取私发红包
     * 该方法不存在事务，避免事务提交前锁被释放
     */
    public ResponseData receivingSingleRedEnvelope(Long id, Long uid) {
        ResponseData responseData = userService.checkUser(uid);
        if (!responseData.isSuccess()) {
            return responseData;
        }
        String key = RedisConstants.GET_SINGLE_RED_ENVELOPE + id.toString();
        try {
            if (redissonUtils.tryLock(key, RedisConstants.WAITTIME, RedisConstants.LEASETIME, RedisConstants.DEFAULT_TIME_UNIT)) {
                return receivedSingleEnvelope(id, uid);
            }
            return ResponseData.failure(RedEnvelopeConstants.RECEIVE_FAILURE);
        } finally {
            redissonUtils.unlock(key);
        }
    }

    @Transactional(rollbackFor = DefaultException.class)
    public ResponseData receivedSingleEnvelope(Long id, Long uid) {
        try {
            RedEnvelope redEnvelope = redEnvelopeMapper.selectByIdAndReceiveIdAndEnvelopeTypeAndEnabledStatus(id, uid, EnvelopeEnum.SINGLE.getCode(), Constants.ENABLEDFLAG);
            //存在且是私发红包
            if (redEnvelope != null) {
                //判断是否领取过了
                RedEnvelopeDetail envelopeDetail = redEnvelopeDetailMapper.selectByEnvelopeIdAndReceiveId(id, uid, Constants.ENABLEDFLAG);
                if (envelopeDetail != null) {
                    return ResponseData.failure(RedEnvelopeConstants.RECEIVED);
                }
                if (redEnvelope.getStatus().equals(EnvelopeStatusEnum.AVAILABLE.getCode())) {
                    //领取详情model
                    RedEnvelopeDetail redEnvelopeDetail = new RedEnvelopeDetail();
                    redEnvelopeDetail.setRedEnvelopeId(redEnvelope.getId());
                    redEnvelopeDetail.setReceiveId(uid);
                    redEnvelopeDetail.setReceiveDate(TimeUtil.now());
                    redEnvelopeDetail.setReceiveMoney(redEnvelope.getAmount());
                    initBaseData(redEnvelopeDetail, Constants.ISNOTUPDATE);
                    if (redEnvelopeDetailMapper.insertSelective(redEnvelopeDetail) <= 0) {
                        throw new DefaultException(RedEnvelopeConstants.RECEIVE_FAILURE);
                    }
                    //更新红包状态
                    redEnvelope.setStatus(EnvelopeStatusEnum.FINISHED.getCode());
                    redEnvelope.setRemainCount(RedEnvelopeConstants.ZERO_SINGLE_RED_ENVE_LOPE_COUNT);
                    initBaseData(redEnvelope, Constants.ISUPDATE);
                    if (redEnvelopeMapper.updateRedEnvelopeStatusAndRemainCount(redEnvelope) <= 0) {
                        throw new DefaultException(RedEnvelopeConstants.RECEIVE_FAILURE);
                    }
                    //用户金额增加
                    if (userMapper.updateMoneyById(uid, redEnvelope.getAmount(), Constants.DEFAULTUPDATEBY) <= 0) {
                        throw new DefaultException(RedEnvelopeConstants.RECEIVE_FAILURE);
                    }
                    return ResponseData.success(redEnvelopeDetail);
                }
            }
            return ResponseData.failure(redEnvelope == null ? RedEnvelopeConstants.NOT_RED_ENVELOPE : redEnvelope.getEnvelopeStatusName());
        } catch (Exception e) {
            log.error("received single redEnvelope error : {}", e.getMessage());
            throw new DefaultException(RedEnvelopeConstants.RECEIVE_FAILURE);
        }
    }

    /**
     * 领取群发红包
     */
    public ResponseData receivingQunRedEnvelope(Long id, Long uid) {
        ResponseData responseData = userService.checkUser(uid);
        if (!responseData.isSuccess()) {
            return responseData;
        }
        String key = RedisConstants.GET_QUN_RED_ENVELOPE + id.toString();
        try {
            if (redissonUtils.tryLock(key, RedisConstants.WAITTIME, RedisConstants.LEASETIME, RedisConstants.DEFAULT_TIME_UNIT)) {
                return receivedQunRedEnvelope(id, uid);
            }
            return ResponseData.failure(RedEnvelopeConstants.RECEIVE_FAILURE);
        } finally {
            redissonUtils.unlock(key);
        }
    }

    @Transactional(rollbackFor = DefaultException.class)
    public ResponseData receivedQunRedEnvelope(Long id, Long uid) {
        try {
            RedEnvelope redEnvelope = redEnvelopeMapper.selectByIdAndTypeAndEnabledFlag(id, EnvelopeEnum.QUN.getCode(), Constants.ENABLEDFLAG);
            //存在且是群发红包
            if (redEnvelope != null) {
                //判断是否在该群组内
                boolean inQun = qunService.isInQun(redEnvelope.getQunId(), uid);
                if (!inQun) {
                    return ResponseData.failure(Constants.NOTEXISTED_IN_QUN);
                }
                //判断是否领取过了
                RedEnvelopeDetail envelopeDetail = redEnvelopeDetailMapper.selectByEnvelopeIdAndReceiveId(id, uid, Constants.ENABLEDFLAG);
                if (envelopeDetail != null) {
                    return ResponseData.failure(RedEnvelopeConstants.RECEIVED);
                }
                if (redEnvelope.getStatus().equals(EnvelopeStatusEnum.AVAILABLE.getCode()) && redEnvelope.getRemainCount() > 0) {
                    //领取详情model
                    RedEnvelopeDetail redEnvelopeDetail = new RedEnvelopeDetail();
                    redEnvelopeDetail.setRedEnvelopeId(redEnvelope.getId());
                    redEnvelopeDetail.setReceiveId(uid);
                    redEnvelopeDetail.setReceiveDate(TimeUtil.now());
                    redEnvelopeDetail.setReceiveMoney(redEnvelope.getAmount());
                    initBaseData(redEnvelopeDetail, Constants.ISNOTUPDATE);
                    if (redEnvelopeDetailMapper.insertSelective(redEnvelopeDetail) <= 0) {
                        throw new DefaultException(RedEnvelopeConstants.RECEIVE_FAILURE);
                    }
                    //更新红包状态
                    redEnvelope.setRemainCount(redEnvelope.getRemainCount() - 1);
                    if (redEnvelope.getRemainCount() <= 0) {
                        redEnvelope.setStatus(EnvelopeStatusEnum.FINISHED.getCode());
                    }
                    initBaseData(redEnvelope, Constants.ISUPDATE);
                    if (redEnvelopeMapper.updateRedEnvelopeStatusAndRemainCount(redEnvelope) <= 0) {
                        throw new DefaultException(RedEnvelopeConstants.RECEIVE_FAILURE);
                    }
                    //用户金额增加
                    if (userMapper.updateMoneyById(uid, redEnvelope.getAmount(), Constants.DEFAULTUPDATEBY) <= 0) {
                        throw new DefaultException(RedEnvelopeConstants.RECEIVE_FAILURE);
                    }
                    return ResponseData.success(redEnvelopeDetail);
                }
            }
            return ResponseData.failure(redEnvelope == null ? RedEnvelopeConstants.NOT_RED_ENVELOPE : redEnvelope.getEnvelopeStatusName());
        } catch (Exception e) {
            log.error("received qun redEnvelope error : {}", e.getMessage());
            throw new DefaultException(RedEnvelopeConstants.RECEIVE_FAILURE);
        }
    }

}