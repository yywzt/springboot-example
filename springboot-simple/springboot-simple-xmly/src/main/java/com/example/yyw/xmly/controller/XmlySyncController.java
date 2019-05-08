package com.example.yyw.xmly.controller;

import com.example.yyw.xmly.exception.BusinessException;
import com.example.yyw.xmly.service.IXmlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/6 16:36
 * @describe 保存至mysql
 */
@RestController
@RequestMapping("/xmlysync")
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

}
