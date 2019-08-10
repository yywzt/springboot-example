package com.example.yyw.xmly.controller;

import com.example.yyw.util.ResultUtil;
import com.example.yyw.xmly.exception.BusinessException;
import com.example.yyw.xmly.service.IXmlyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 构建喜马拉雅声音碎片缓存
     *
     * @return
     */
    @RequestMapping(value = "/buildXimalayaTrackCache")
    public Object buildXimalayaTrackCache(){
        iXmlyService.buildXimalayaTrackCache();
        return ResultUtil.successResult();
    }
    @RequestMapping(value = "/buildXimalayaTrackCacheTmp")
    public Object buildXimalayaTrackCacheTmp(){
        iXmlyService.buildXimalayaTrackCacheTmp();
        return ResultUtil.successResult();
    }

    @RequestMapping("/saveTrackByAlbum")
    public Object saveTrackByAlbum(Long albumId) throws BusinessException {
        iXmlyService.saveTrackByAlbumId(albumId);
        return ResultUtil.successResult();
    }

    @RequestMapping("/syncStatus")
    public Object syncStatus(){
        return iXmlyService.syncStatus();
    }

    @RequestMapping("/renameRedisKey")
    public void renameRedisKey(){
        iXmlyService.alterXmlyRedisKey();
    }

    @RequestMapping("/getEff")
    public String getEff(){
        return iXmlyService.getEff();
    }

}
