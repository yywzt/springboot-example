package com.example.yyw.controller;

import com.example.yyw.constant.ResponseData;
import com.example.yyw.dto.RedEnvelopeDto;
import com.example.yyw.service.redEnvelope.RedEnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/4/23 16:16
 * @describe
 */
@RestController
@RequestMapping("/redEnvelope")
public class RedEnvelopeController {

    @Autowired
    private RedEnvelopeService redEnvelopeService;

    @GetMapping("/getAllRedEnvelope")
    public ResponseData getAllRedEnvelope() {
        return redEnvelopeService.getAllRedEnvelope();
    }

    /**
     * 红包详情
     *
     * @param id 红包id
     */
    @GetMapping(value = "/getRedEnvelope/{id}")
    public ResponseData getRedEnvelope(@PathVariable("id") Long id) {
        return redEnvelopeService.getRedEnvelope(id);
    }

    /**
     * 私发红包
     *
     * @param redEnvelopeDto
     */
    @PostMapping(value = "/sendSingleRedEnvelope")
    public ResponseData sendSingleRedEnvelope(@Validated RedEnvelopeDto redEnvelopeDto) {
        return redEnvelopeService.sendSingleRedEnvelope(redEnvelopeDto);
    }

    /**
     * 群发红包
     *
     * @param redEnvelopeDto
     */
    @PostMapping(value = "/sendQunRedEnvelope")
    public ResponseData sendQunRedEnvelope(@Validated RedEnvelopeDto redEnvelopeDto) {
        return redEnvelopeService.sendQunRedEnvelope(redEnvelopeDto);
    }

    /**
     * 领取私发红包
     *
     * @param id  红包id
     * @param uid 领取人id
     */
    @PostMapping(value = "/receivingSingleRedEnvelope")
    public ResponseData receivingSingleRedEnvelope(@RequestParam Long id, @RequestParam Long uid) {
        return redEnvelopeService.receivingSingleRedEnvelope(id, uid);
    }

    /**
     * 领取群发红包
     *
     * @param id  红包id
     * @param uid 领取人id
     */
    @PostMapping(value = "/receivingQunRedEnvelope")
    public ResponseData receivingQunRedEnvelope(@RequestParam Long id, @RequestParam Long uid) {
        return redEnvelopeService.receivingQunRedEnvelope(id, uid);
    }

}
