package com.example.yyw.service.redEnvelope;

import com.example.yyw.constant.Constants;
import com.example.yyw.constant.ResponseData;
import com.example.yyw.mapper.redEnvelope.QunMapper;
import com.example.yyw.mapper.redEnvelope.UserQunMapper;
import com.example.yyw.model.redEnvelope.Qun;
import com.example.yyw.model.redEnvelope.UserQun;
import com.example.yyw.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanzt
 * @date 2019/4/24 23:15
 * @describe
 */
@Service
public class QunService extends GenericService<Long> {

    @Autowired
    private QunMapper qunMapper;

    @Autowired
    private UserQunMapper userQunMapper;

    @Autowired
    private UserService userService;

    /**
     * 校验群组是否存在且有效
     * @param id 群组id
     * @return
     */
    public ResponseData checkQun(Long id){
        Qun qun = qunMapper.selectByPrimaryKey(id);
        if(qun != null && qun.getEnabledFlag().equals(Constants.ENABLEDFLAG)){
            return ResponseData.success(qun);
        }else{
            return ResponseData.failure(Constants.QUN_INVALID);
        }
    }

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
        Qun vo = qunMapper.selectByQunName(qun.getQunName(), Constants.ENABLEDFLAG);
        if(vo != null){
            return ResponseData.failure(Constants.EXISET_QUN_NAME);
        }
        initBaseData(qun,Constants.ISNOTUPDATE);
        if(qunMapper.insertSelective(qun) > 0 ){
            return ResponseData.success();
        }
        return ResponseData.failure();
    }

    /**
     * 加入群组
     * @param userQun
     * @return
     */
    public ResponseData joinQun(UserQun userQun) {
        ResponseData qunResult = checkQun(userQun.getQunId());
        ResponseData userResult = userService.checkUser(userQun.getUserId());
        if(!qunResult.isSuccess() || !userResult.isSuccess()){
            return ResponseData.failure(Constants.USER_OR_QUN_INVALID);
        }
        //判断是否已在该群组内
        boolean inQun = isInQun(userQun.getQunId(), userQun.getUserId());
        if(inQun){
            return ResponseData.failure(Constants.EXISTED_IN_QUN);
        }
        initBaseData(userQun,Constants.ISNOTUPDATE);
        if(userQunMapper.insertSelective(userQun) > 0 ){
            return ResponseData.success(Constants.JOININ_QUN_SUCCESS);
        }
        return ResponseData.failure(Constants.JOININ_QUN_FAILURE);
    }

    /**
     * 判断用户是否在群组内
     * @param qunId
     * @param userId
     * @return true:已在该群组内,false:不在该群组内
     */
    public boolean isInQun(Long qunId,Long userId){
        UserQun vo = userQunMapper.selectByQunIdAndUserId(qunId, userId, Constants.ENABLEDFLAG);
        return vo == null ? false : true;
    }

    /**
     * 退出群组
     * @param userQun
     * @return
     */
    public ResponseData quitQun(UserQun userQun) {
        int i = userQunMapper.quitQun(userQun.getQunId(), userQun.getUserId(), Constants.DISENABLEDFLAG,Constants.ENABLEDFLAG,Constants.DEFAULTUPDATEBY);
        if(i > 0){
            return ResponseData.success(Constants.QUIT_QUN_SUCCESS);
        }
        return ResponseData.failure(Constants.QUIT_QUN_FAILURE);
    }
}
