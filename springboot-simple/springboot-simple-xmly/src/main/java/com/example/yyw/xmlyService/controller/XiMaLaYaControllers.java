package com.example.yyw.xmlyService.controller;

import com.example.yyw.util.ResultUtil;
import com.example.yyw.xmlyService.service.XiMaLaYaService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/ximalaya")
public class XiMaLaYaControllers {
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
     * @return Object
     */
    @RequestMapping("/album/getUpdateBatch")
    public Object getUpdateBatch(@RequestParam Long[] ids){
        return ResultUtil.successResult(xiMaLaYaService.getUpdateBatch(ids));
    }

    /**
     * 获取全量专辑数据（注意已剔除版权权益不明确的专辑数据）
     * @param categoryId 分类ID
     * @param pageable  返回第几页，从1开始，默认为1,每页大小，范围为[1,200]，默认为20
     * @return Object
     */
    @RequestMapping("/album/getAlbumAll")
    public Object getAlbumAll(@RequestParam Long categoryId, Pageable pageable){
        return xiMaLaYaService.getAlbumAll(categoryId, pageable);
    }
    /**
     * 获取专辑增量数据
     *     获取某个分类中某标签在指定时间区间内的专辑增量数据
     * https://open.ximalaya.com/doc/podcast-data-sync#%E8%8E%B7%E5%8F%96%E4%B8%93%E8%BE%91%E5%A2%9E%E9%87%8F%E6%95%B0%E6%8D%AE
     * @param categoryId 分类ID
     * @param startDate 时间区间的开始时间 yyyy-MM-dd HH:mm:ss
     * @param endDate 时间区间的结束时间 yyyy-MM-dd HH:mm:ss
     * @param pageable  返回第几页，从1开始，默认为1,每页大小，范围为[1,200]，默认为20
     * @return Object
     */
    @RequestMapping("/album/getIncrementAlbums")
    public Object getIncrementAlbums(@RequestParam Long categoryId, long startDate ,long endDate, Pageable pageable){
        return xiMaLaYaService.getIncrementAlbums(categoryId, startDate, endDate, pageable);
    }
    @RequestMapping("/album/getIncrementTracks")
    public Object getIncrementTracks(@RequestParam Long albumId, long startDate ,long endDate, Pageable pageable){
        return xiMaLaYaService.getIncrementTracks(albumId, startDate, endDate, pageable);
    }
    /**
     * 获取专辑增量数据
     *     获取某个分类中某标签在指定时间区间内的专辑增量数据
     * https://open.ximalaya.com/doc/podcast-data-sync#%E8%8E%B7%E5%8F%96%E4%B8%93%E8%BE%91%E5%A2%9E%E9%87%8F%E6%95%B0%E6%8D%AE
     * @param categoryId 分类ID
     * @param start_time 时间区间的开始时间 yyyy-MM-dd HH:mm:ss
     * @param end_time 时间区间的结束时间 yyyy-MM-dd HH:mm:ss
     * @param page  返回第几页，从1开始，默认为1
     * @param count 每页大小，范围为[1,200]，默认为20
     * @return Object
     */
    /*@RequestMapping("/album/getIncrementAlbums")
    public Object getIncrementAlbums(@RequestParam Long categoryId, String start_time ,String end_time, int page, int count){
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = DateUtils.parseDate(start_time, "yyyy-MM-dd HH:mm:ss");
            endDate = DateUtils.parseDate(end_time, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("date parse error : {}",e.getMessage());
        }
        return ResultUtil.successResult(xiMaLaYaService.getIncrementAlbums(categoryId, startDate, endDate, page, count));
    }
    @RequestMapping("/album/getIncrementTracks")
    public Object getIncrementTracks(@RequestParam Long albumId, String start_time ,String end_time, int page, int count){
        LocalDateTime startDate = TimeUtil.stringToLocalDateTime(start_time);
        LocalDateTime endDate = TimeUtil.stringToLocalDateTime(end_time);
        return ResultUtil.successResult(xiMaLaYaService.getIncrementTracks(albumId, startDate, endDate, page, count));
    }*/

}
