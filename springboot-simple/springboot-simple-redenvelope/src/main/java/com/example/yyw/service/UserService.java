package com.example.yyw.service;

import com.example.yyw.constant.Constants;
import com.example.yyw.constant.ResponseData;
import com.example.yyw.mapper.redEnvelope.UserMapper;
import com.example.yyw.model.redEnvelope.User;
import com.example.yyw.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yanzt
 * @date 2019/4/24 22:25
 * @describe
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    /**
     * 用户余额变更
     * @param id
     * @param money >0:增加,<0:减少
     * @return
     */
    public ResponseData addMoneyByUserId(Long id, BigDecimal money){
        return ResponseData.success();
    }

    /**
     * 使用unique index避免用户名重复
     * @param user
     * @return
     */
    /*public ResponseData initUser(User user) {
        String key = RedisConstants.INITUSER + user.getUserName();
        try {
            if (redissonUtils.tryLock(key, RedisConstants.WAITTIME, RedisConstants.LEASETIME, RedisConstants.DEFAULT_TIME_UNIT)) {
                User vo = userMapper.selectByUserName(user.getUserName(), Constants.ENABLEDFLAG);
                if (vo != null) {
                    return ResponseData.failure("该用户名已存在");
                }
                user.setMoney(BigDecimal.ZERO);
                user.setCreationDate(DateUtil.getNowTimestamp());
                user.setCreatedBy(Constants.DEFAULTCREATEBY);
                if (userMapper.insertSelective(user) > 0) {
                    return ResponseData.success();
                }
            }
        }finally {
            redissonUtils.unlock(key);
        }
        return ResponseData.failure();
    }*/
    public ResponseData initUser(User user) {
        user.setMoney(BigDecimal.ZERO);
        user.setCreationDate(DateUtil.getNowTimestamp());
        user.setCreatedBy(Constants.DEFAULTCREATEBY);
        if (userMapper.insertSelective(user) > 0) {
            return ResponseData.success();
        }
        return ResponseData.failure();
    }
}
