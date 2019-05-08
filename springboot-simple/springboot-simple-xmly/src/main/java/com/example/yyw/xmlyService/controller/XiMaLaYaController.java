package com.example.yyw.xmlyService.controller;

import com.example.yyw.util.ResultUtil;
import com.example.yyw.xmlyService.service.XiMaLaYaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 喜马拉雅服务接口集合
 *
 * @author yanzhitao
 **/
@RestController
@RequestMapping("/ximalaya")
public class XiMaLaYaController {
    @Autowired
    private XiMaLaYaService xiMaLaYaService;

    @RequestMapping("/category/list")
    public Object getCategoryList(){
        return ResultUtil.successResult(xiMaLaYaService.getCategoryList());
    }

    @RequestMapping("/tag/list")
    public Object getTagList(Long categoryId, @RequestParam(defaultValue = "0") int tagType){
        return ResultUtil.successResult(xiMaLaYaService.getTagList(categoryId, tagType));
    }

    @RequestMapping("/album/list")
    public Object getAlbumList(Long categoryId, String albumTagName, @RequestParam(defaultValue = "1") Integer calcDimension, Pageable pageable){
        return xiMaLaYaService.getAlbumList(categoryId, albumTagName, calcDimension, pageable);
    }

    @RequestMapping("/album/batch")
    public Object getAlbumBatch(Long[] ids){
        return ResultUtil.successResult(xiMaLaYaService.albumGetBatch(ids));
    }

    @RequestMapping("/track/batch")
    @ResponseBody
    public Object getTrackBatch(Long[] ids){
        return ResultUtil.successResult(xiMaLaYaService.trackGetBatch(ids, false));
    }

    @RequestMapping("/album/guessLike")
    public Object guessLikeAlbums(String channelId, String uid, @RequestParam(defaultValue = "2") int deviceType,
                                  @RequestParam(defaultValue = "10") int count){
        return ResultUtil.successResult(xiMaLaYaService.guessLikeAlbums(deviceType, channelId, uid, count));
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

    /**
     * 根据一批专辑ID批量获取专辑更新信息（专辑下最新上传或更新的声音信息）
     * @param ids 专辑ID
     * @return
     */
    @RequestMapping("/album/getUpdateBatch")
    public Object getUpdateBatch(@RequestParam Long[] ids){
        return ResultUtil.successResult(xiMaLaYaService.getUpdateBatch(ids));
    }
}
