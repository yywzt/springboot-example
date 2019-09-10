package com.example.yyw.xmlyService.controller;

import com.example.yyw.util.ResultUtil;
import com.example.yyw.xmly.response.OpenPushResponse;
import com.example.yyw.xmly.service.IXmlyService;
import com.example.yyw.xmlyService.service.XiMaLaYaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 喜马拉雅服务接口集合
 *
 * @author yanzhitao
 **/
@Slf4j
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

    @Autowired
    private IXmlyService iXmlyService;

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
