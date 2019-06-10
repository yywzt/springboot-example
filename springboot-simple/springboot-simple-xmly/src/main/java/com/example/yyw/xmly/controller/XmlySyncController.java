package com.example.yyw.xmly.controller;

import com.example.yyw.util.ResultUtil;
import com.example.yyw.xmly.exception.BusinessException;
import com.example.yyw.xmly.response.OpenPushResponse;
import com.example.yyw.xmly.service.IXmlyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/6 16:36
 * @describe 保存至mysql
 */
@RestController
@RequestMapping("/xmlysync")
@Slf4j
public class XmlySyncController {

    @Autowired
    private IXmlyService iXmlyService;

    @RequestMapping("/saveCategory")
    public Object saveCategory() throws BusinessException {
        return iXmlyService.saveCategory();
    }

    @RequestMapping("/saveAlbum")
    public Object saveAlbum() throws BusinessException {
        return iXmlyService.saveAlbum();
    }

    @RequestMapping("/saveTrack")
    public Object saveTrack() throws BusinessException {
        return iXmlyService.saveTrack();
    }

    @RequestMapping("/saveIncrementAlbum")
    public Object saveIncrementAlbum() throws BusinessException {
        iXmlyService.saveIncrementAlbum();
        return ResultUtil.successResult();
    }

    @RequestMapping("/saveIncrementTrack")
    public Object saveIncrementTrack() throws BusinessException {
        iXmlyService.saveIncrementTrack();
        iXmlyService.saveTrack();
        return ResultUtil.successResult();
    }

    @RequestMapping("/saveAll")
    public Object saveAll() throws BusinessException{
        iXmlyService.saveAll();
        return ResultUtil.successResult();
    }

    /**
     * 实时推送专辑/声音的上下架状态
     * @param app_key	String	是	喜马拉雅开放平台应用公钥
     * @param push_type	Int	是	推送内容类型：1-专辑，2-声音
     * @param id	Int	是	推送内容ID，即专辑ID（push_type为1时）或声音ID（push_type为2时）
     * @param subordinated_album_id	Int	否	如果推送内容类型为声音时，有此字段，表示声音所属专辑ID
     * @param is_paid	Bool	否	是否是付费内容：true-付费，false-免费。没有该参数时默认为免费内容
     * @param updated_at	Int	是	业务发生时间（即发生上下架事件的时刻），Unix毫秒数时间戳
     * @param is_online	Bool	是	内容上下架状态：true-上架，false-下架
     * @param offline_reason_type	Int	是	下架原因： 0-无此属性，1-运营/主播下架内容，2-版权变更导致内容不再输出
     * @param nonce	String	是	随机字符串
     * @param timestamp	Int	是	Unix毫秒数时间戳
     * @param sig	String	是	签名参数，注意这里的sig参数生成算法不同于接入指南的通用签名生成算法，具体请参考 合作方实现的接口的签名生成算法
     *
     * @return json 字段为：
     *          code    Int	推送结果：0-成功，1-失败
     *          message	String	可选，失败时为出错描述
     *          source	String	必填，唯一标识推送接口提供方来源，需要合作方和喜马拉雅共同约定
     */
    @RequestMapping("/open_push")
    public OpenPushResponse openPush(String app_key, Integer push_type, Integer id, Integer subordinated_album_id, Boolean is_paid, Long updated_at,
                           Boolean is_online, Integer offline_reason_type, String nonce, Long timestamp, String sig){
        Map<String, Object> params = iXmlyService.buildParams(app_key, push_type, id, subordinated_album_id, is_paid, updated_at, is_online, offline_reason_type, nonce, timestamp);
        if(iXmlyService.verifySign(params).equals(sig)) {
            return iXmlyService.openPush(push_type, id, subordinated_album_id, is_paid, updated_at, is_online, offline_reason_type, nonce, timestamp);
        }else{
            log.info("sig验证失败");
            return OpenPushResponse.failure("签名验证失败");
        }
    }

}
