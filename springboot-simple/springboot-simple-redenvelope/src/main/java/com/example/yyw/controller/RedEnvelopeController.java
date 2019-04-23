package com.example.yyw.controller;

import com.example.yyw.constant.ResponseData;
import com.example.yyw.dto.RedEnvelopeDto;
import com.example.yyw.service.RedEnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/23 16:16
 * @describe
 */
@RestController
public class RedEnvelopeController {

    @Autowired
    private RedEnvelopeService redEnvelopeService;

    /**
     * 红包详情
     * @param id 红包id
     * */
    @RequestMapping(value = "/getRedEnvelope/{id}",method = RequestMethod.GET)
    public ResponseData getRedEnvelope(@PathVariable("id") Long id){
        return redEnvelopeService.getRedEnvelope(id);
    }

    /**
     * 私发红包
     * @param redEnvelopeDto
     * */
    @RequestMapping(value = "/sendSingleRedEnvelope",method = RequestMethod.POST)
    public ResponseData sendSingleRedEnvelope(@Validated RedEnvelopeDto redEnvelopeDto){
        return redEnvelopeService.sendSingleRedEnvelope(redEnvelopeDto);
    }
    /**
     * 群发红包
     * @param redEnvelopeDto
     * */
    @RequestMapping(value = "/sendQunRedEnvelope",method = RequestMethod.POST)
    public ResponseData sendQunRedEnvelope(@Validated RedEnvelopeDto redEnvelopeDto){
        return redEnvelopeService.sendQunRedEnvelope(redEnvelopeDto);
    }
    /**
     * 群发红包
     * @param id 红包id
     * @param uid 领取人id
     * */
    @RequestMapping(value = "/receivingRedEnvelope",method = RequestMethod.POST)
    public ResponseData receivingRedEnvelope(@RequestParam Long id,@RequestParam Long uid){
        return redEnvelopeService.receivingRedEnvelope(id,uid);
    }

}
