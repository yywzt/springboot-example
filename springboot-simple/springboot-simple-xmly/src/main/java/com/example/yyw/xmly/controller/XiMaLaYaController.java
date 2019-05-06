package com.example.yyw.xmly.controller;

import com.example.yyw.xmly.service.XiMaLaYaService;
import com.example.yyw.xmly.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 喜马拉雅服务接口集合
 *
 * @author buxianglong
 **/
@RestController
@RequestMapping("/ximalaya")
public class XiMaLaYaController {
    @Autowired
    private XiMaLaYaService xiMaLaYaService;

    @RequestMapping("/category/list")
    public Object getCategoryList(){
        return ToolUtil.successResult(xiMaLaYaService.getCategoryList());
    }

    @RequestMapping("/tag/list")
    public Object getTagList(Long categoryId, @RequestParam(defaultValue = "0") int tagType){
        return ToolUtil.successResult(xiMaLaYaService.getTagList(categoryId, tagType));
    }

    @RequestMapping("/album/list")
    public Object getAlbumList(Long categoryId, String albumTagName, @RequestParam(defaultValue = "1") Integer calcDimension, Pageable pageable){
        return xiMaLaYaService.getAlbumList(categoryId, albumTagName, calcDimension, pageable);
    }

    @RequestMapping("/album/batch")
    public Object getAlbumBatch(Long[] ids){
        return ToolUtil.successResult(xiMaLaYaService.albumGetBatch(ids));
    }

    @RequestMapping("/track/batch")
    @ResponseBody
    public Object getTrackBatch(Long[] ids){
        return ToolUtil.successResult(xiMaLaYaService.trackGetBatch(ids, false));
    }

    @RequestMapping("/album/guessLike")
    public Object guessLikeAlbums(String channelId, String uid, @RequestParam(defaultValue = "2") int deviceType,
                                  @RequestParam(defaultValue = "10") int count){
        return ToolUtil.successResult(xiMaLaYaService.guessLikeAlbums(deviceType, channelId, uid, count));
    }

    @RequestMapping("/track/guessLike")
    public Object guessLikeTracks(String channelId, String uid, @RequestParam(defaultValue = "2") int deviceType,
                                  Pageable pageable){
        return xiMaLaYaService.guessLikeTracks(deviceType, channelId, uid, pageable);
    }

    @RequestMapping("/track/byAlbum")
    public Object getTracksByAlbum(Long albumId, @RequestParam(defaultValue = "asc") String sort, Pageable pageable){
        return xiMaLaYaService.albumBrowse(albumId, sort, pageable);
    }
}
