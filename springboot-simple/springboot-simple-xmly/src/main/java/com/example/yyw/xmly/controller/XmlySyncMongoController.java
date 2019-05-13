package com.example.yyw.xmly.controller;

import com.example.yyw.util.ResultUtil;
import com.example.yyw.xmly.exception.BusinessException;
import com.example.yyw.xmly.service.IXmlyMongoService;
import com.example.yyw.xmly.service.IXmlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/6 16:36
 * @describe 保存至mongodb中
 */
@RestController
@RequestMapping("/xmlysyncMongo")
public class XmlySyncMongoController {

    @Autowired
    private IXmlyMongoService iXmlyMongoService;

    @RequestMapping("/saveCategory")
    public Object saveCategory() throws BusinessException {
        return iXmlyMongoService.saveCategory();
    }

    @RequestMapping("/saveAlbum")
    public Object saveAlbum() throws BusinessException {
        return iXmlyMongoService.saveAlbum();
    }

    @RequestMapping("/saveTrack")
    public Object saveTrack() throws BusinessException {
        return iXmlyMongoService.saveTrack();
    }

    /**
     * 构建喜马拉雅声音碎片缓存
     *
     * @return
     */
    @RequestMapping(value = "/buildXimalayaTrackCache")
    public Object buildXimalayaTrackCache(){
        iXmlyMongoService.buildXimalayaTrackCache();
        return ResultUtil.successResult();
    }
}
