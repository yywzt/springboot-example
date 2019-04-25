package com.example.yyw.service;

import com.example.yyw.constant.Constants;
import com.example.yyw.constant.ResponseData;
import com.example.yyw.mapper.redEnvelope.QunMapper;
import com.example.yyw.model.redEnvelope.Qun;
import com.example.yyw.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanzt
 * @date 2019/4/24 23:15
 * @describe
 */
@Service
public class QunService {

    @Autowired
    private QunMapper qunMapper;

    /**
     * 数据库对qun_name创建unique索引
     * @param qun
     * @return
     */
    /*public ResponseData initQun(Qun qun){
        String key = RedisConstants.INITQUN + qun.getQunName();
        try {
            if (redissonUtils.tryLock(key, RedisConstants.WAITTIME,RedisConstants.LEASETIME, RedisConstants.DEFAULT_TIME_UNIT)){
                Qun vo = qunMapper.selectByQunName(qun.getQunName(), Constants.ENABLEDFLAG);
                if(vo != null){
                    return ResponseData.failure("群组名称已存在");
                }
                qun.setCreationDate(DateUtil.getNowTimestamp());
                qun.setCreatedBy(Constants.DEFAULTCREATEBY);
                if(qunMapper.insertSelective(qun) > 0 ){
                    return ResponseData.success();
                }
            }
        } finally {
            redissonUtils.unlock(key);
        }
        return ResponseData.failure();
    }*/
    public ResponseData initQun(Qun qun){
        qun.setCreationDate(DateUtil.getNowTimestamp());
        qun.setCreatedBy(Constants.DEFAULTCREATEBY);
        if(qunMapper.insertSelective(qun) > 0 ){
            return ResponseData.success();
        }
        return ResponseData.failure();
    }

}
