package com.example.yyw.xmly.service;

import com.example.yyw.util.HttpUtil;
import com.example.yyw.util.JsonBinder;
import com.example.yyw.util.ToolUtil;
import com.example.yyw.xmly.modal.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.yyw.xmly.service.XiMaLaYaSignService.buildParamUrl;
import static com.example.yyw.xmly.service.XiMaLaYaSignService.buildParams;


/**
 * 喜马拉雅API服务
 *
 * @author yanzhitao
 **/
@Service
public class XiMaLaYaService{
    private static final String DOMAIN = "https://api.ximalaya.com";
    private static final String SEARCH_ALBUMS_URL = DOMAIN + "/search/albums";
    private static final String CATEGORY_LIST = DOMAIN + "/categories/list";
    private static final String TAGS_LIST = DOMAIN + "/v2/tags/list";
    private static final String ALBUM_LIST = DOMAIN + "/v2/albums/list";
    private static final String ALBUM_BROWSE = DOMAIN + "/albums/browse";
    private static final String ALBUM_GET_BATCH = DOMAIN + "/albums/get_batch";
    private static final String TRACK_GET_BATCH = DOMAIN + "/tracks/get_batch";
    private static final String META_DATA_LIST = DOMAIN + "/metadata/list";
    private static final String META_DATA_ALBUMS = DOMAIN + "/metadata/albums";
    private static final String RANK_INDEX_LIST = DOMAIN + "/v3/ranks/index_list";
    private static final String RANK_ALBUMS = DOMAIN + "/v3/ranks/albums";
    private static final String TRACK_PLAY_RECORD_POST_BATCH = DOMAIN + "/openapi-collector-app/track_batch_records";
    private static final String GUESS_LIKE_ALBUMS = DOMAIN + "/v2/albums/guess_like";
    private static final String ONE_CLICK_LISTEN_CHANNEL_LIST = DOMAIN + "/one_click_listen/channels";

    /**
     * 点播数据API：分类列表
     */
    public List<Category> getCategoryList(){
        String url = CATEGORY_LIST + "?" + buildParamUrl(new HashMap<>(0));
        String response = HttpUtil.httpGet(url);
        return JsonBinder.buildNonNullBinder().fromJson(response, new TypeReference<List<Category>>(){
        });
    }

    /**
     * 点播数据API： 标签列表
     *
     * @param categoryId 分类ID，0表示热门分类
     * @param tagType    标签类型
     */
    public List<Tag> getTagList(Long categoryId, int tagType){
        return getTagList(categoryId, TagTypeEnum.findByCode(tagType));
    }

    /**
     * 点播数据API： 标签列表
     *
     * @param categoryId  分类ID，0表示热门分类
     * @param tagTypeEnum 标签类型枚举
     */
    private List<Tag> getTagList(Long categoryId, TagTypeEnum tagTypeEnum){
        Map<String, String> params = Maps.newHashMap();
        params.put("category_id", categoryId + "");
        params.put("type", tagTypeEnum.getCode() + "");
        String url = TAGS_LIST + "?" + buildParamUrl(params);
        String response = HttpUtil.httpGet(url);
        return JsonBinder.buildNonNullBinder().fromJson(response, new TypeReference<List<Tag>>(){
        });
    }

    /**
     * 按分类和标签，获取专辑集合
     *
     * @param categoryId    分类ID
     * @param albumTagName  专辑标签
     * @param calcDimension 计算维度
     * @param pageable      分页信息
     * @return
     */
    public Object getAlbumList(Long categoryId, String albumTagName, int calcDimension, Pageable pageable){
        int page = pageable.getPageNumber() + 1;
        int count = pageable.getPageSize();
        CategoryBrowse categoryBrowse = getAlbumList(categoryId, albumTagName, CalcDimensionEnum.findByCode(calcDimension), page, count);
        Page<Album> albumPage = new PageImpl<>(categoryBrowse.getAlbums(), pageable, categoryBrowse.getTotalCount());
        return ToolUtil.checkPageResult(albumPage, albumPage.getContent());
    }

    /**
     * 点播数据API：专辑列表
     *
     * @param categoryId        分类ID
     * @param albumTagName      专辑标签
     * @param calcDimensionEnum 计算维度枚举
     * @param page              页码， 从1开始，默认为1
     * @param count             每页数量，范围[1,200], 默认20
     * @return 专辑列表
     */
    public CategoryBrowse getAlbumList(Long categoryId, String albumTagName, CalcDimensionEnum calcDimensionEnum, int page, int count){
        Map<String, String> params = Maps.newHashMap();
        params.put("category_id", categoryId + "");
        if(StringUtils.isNotBlank(albumTagName)){
            params.put("tag_name", albumTagName);
        }
        params.put("calc_dimension", calcDimensionEnum.getCode() + "");
        params.put("page", page + "");
        params.put("count", count + "");
        params.put("contains_paid", "false");
        String url = ALBUM_LIST + "?" + buildParamUrl(params);
        String response = HttpUtil.httpGet(url);
        return JsonBinder.buildNonNullBinder().fromJson(response, CategoryBrowse.class);
    }

    /**
     * 点播数据API：批量获取专辑信息
     *
     * @param albumIds 专辑ID集合,最多200个
     */
    public List<Album> albumGetBatch(Long[] albumIds){
        Map<String, String> params = Maps.newHashMap();
        String ids = Lists.newArrayList(albumIds).stream().map(albumId -> albumId + "").collect(Collectors.joining(","));
        params.put("ids", ids);
        String url = ALBUM_GET_BATCH + "?" + buildParamUrl(params);
        String response = HttpUtil.httpGet(url);
        return JsonBinder.buildNonNullBinder().fromJson(response, new TypeReference<List<Album>>(){
        });
    }

    /**
     * 专辑浏览
     *
     * @param albumId  专辑ID
     * @param sort     排序方式
     * @param pageable 分页信息，页码从0开始，默认为0
     * @return
     */
    public Object albumBrowse(Long albumId, String sort, Pageable pageable){
        int page = pageable.getPageNumber() + 1;
        int count = pageable.getPageSize();
        AlbumBrowse albumBrowse = albumBrowse(albumId, SortEnum.getByCode(sort), page, count);
        Long totalCount = 0L;
        List<Track> trackList = null;
        if(null != albumBrowse){
            totalCount = albumBrowse.getTotalCount();
            trackList = albumBrowse.getTracks();
        }
        Page<Track> trackPage = new PageImpl<>(trackList, pageable, totalCount);
        return ToolUtil.checkPageResult(trackPage, trackPage.getContent());
    }

    /**
     * 专辑浏览
     *
     * @param albumId  专辑ID
     * @param sortEnum 排序方式
     * @param page     页码， 从1开始，默认为1
     * @param count    每页数量，范围[1,200], 默认20
     */
    public AlbumBrowse albumBrowse(Long albumId, SortEnum sortEnum, int page, int count){
        Map<String, String> params = Maps.newHashMap();
        params.put("album_id", albumId.toString());
        params.put("sort", sortEnum.getCode() + "");
        params.put("page", page + "");
        params.put("count", count + "");
        String url = ALBUM_BROWSE + "?" + buildParamUrl(params);
        String response = HttpUtil.httpGet(url);
        AlbumBrowse albumBrowse = JsonBinder.buildNonNullBinder().fromJson(response, AlbumBrowse.class);
        return albumBrowse;
    }

    /**
     * 点播数据API：批量获取声音信息
     *
     * @param ids 声音ID集合,最多200个
     */
    public List<Track> trackGetBatch(Long[] ids, boolean onlyPlayInfo){
        Map<String, String> params = Maps.newHashMap();
        String idStrs = Arrays.asList(ids).stream().map(trackId -> trackId + "").collect(Collectors.joining(","));
        params.put("ids", idStrs);
        params.put("only_play_info", onlyPlayInfo + "");
        String url = TRACK_GET_BATCH + "?" + buildParamUrl(params);
        String response = HttpUtil.httpGet(url);
        Tracks tracks = JsonBinder.buildNonNullBinder().fromJson(response, Tracks.class);
        return null == tracks ? null : tracks.getTracks();
    }

    /**
     * 点播数据API：获取某个分类下的元数据列表
     *
     * @param categoryId
     */
    public void metaDataList(int categoryId){
        Map<String, String> params = Maps.newHashMap();
        params.put("category_id", categoryId + "");
        System.out.println("获取某个分类下的元数据列表:" + META_DATA_LIST + "?" + buildParamUrl(params));
    }

    /**
     * 点播数据API：获取元数据下的专辑列表
     *
     * @param categoryId            分类ID
     * @param metaDataAttributeMaps metaData元数据属性键值对，键为attrKey，值为attrValue
     * @param calcDimensionEnum     返回结果排序维度
     * @param page                  页码， 从1开始，默认为1
     * @param count                 每页数量，范围[1,200], 默认20
     */
    public void getAlbumsByMetaData(int categoryId, Map<Integer, String> metaDataAttributeMaps, CalcDimensionEnum calcDimensionEnum, int page, int count){
        Map<String, String> params = Maps.newHashMap();
        params.put("category_id", categoryId + "");
        StringBuilder metaDataAttributes = new StringBuilder();
        for(Map.Entry<Integer, String> entry : metaDataAttributeMaps.entrySet()){
            metaDataAttributes.append(entry.getKey() + ":" + entry.getValue() + ";");
        }
        params.put("metadata_attributes", metaDataAttributes.toString());
        params.put("calc_dimension", calcDimensionEnum.getCode() + "");
        params.put("page", page + "");
        params.put("count", count + "");
        System.out.println("获取元数据下的专辑列表:" + META_DATA_ALBUMS + "?" + buildParamUrl(params));
    }

    /**
     * 点播数据API：获取专辑榜单列表
     *
     * @param rankTypeEnum
     */
    public void getRankIndexList(RankTypeEnum rankTypeEnum){
        Map<String, String> params = Maps.newHashMap();
        params.put("rank_type", rankTypeEnum.getCode() + "");
        System.out.println("获取专辑榜单列表:" + RANK_INDEX_LIST + "?" + buildParamUrl(params));
    }

    /**
     * 点播数据API：获取专辑榜单详情
     *
     * @param rankListId 榜单ID
     * @param page       页码， 从1开始，默认为1
     * @param count      每页数量，范围[1,200], 默认20
     */
    public void getRankAlbums(String rankListId, int page, int count){
        Map<String, String> params = Maps.newHashMap();
        params.put("rank_list_id", rankListId);
        params.put("page", page + "");
        params.put("count", count + "");
        System.out.println("获取专辑榜单详情:" + RANK_ALBUMS + "?" + buildParamUrl(params));
    }

    /**
     * 播放数据回传API：批量回传播放数据
     *
     * @param deviceId
     * @param trackPlayRecordList
     */
    public void batchPostTrackRecords(String deviceId, List<TrackPlayRecord> trackPlayRecordList){
        Map<String, String> params = Maps.newHashMap();
        params.put("device_id", deviceId);
        String trackRecords = JsonBinder.buildNonNullBinder().toJson(trackPlayRecordList);
        params.put("track_records", trackRecords);
        System.out.println("批量回传播放数据:" + TRACK_PLAY_RECORD_POST_BATCH);
        Map<String, String> allParams = buildParams(params, false);
        System.out.println(allParams);
    }

    public List<Album> guessLikeAlbums(int deviceType, String channelId, String uid, int likeCount){
        if(StringUtils.isBlank(uid)){
            return new ArrayList<>();
        }
        DeviceTypeEnum deviceTypeEnum = DeviceTypeEnum.findByCode(deviceType);
        String deviceId = uid.toString();
        return guessLikeAlbums(deviceTypeEnum, deviceId, likeCount);
    }

    public Object guessLikeTracks(int deviceType, String channelId, String uid, Pageable pageable){
        List<Album> albumList = guessLikeAlbums(deviceType, channelId, uid, 1);
        Long albumId = albumList.get(0).getId();
        return albumBrowse(albumId, SortEnum.ASC.getCode(), pageable);
    }

    /**
     * 获取猜你喜欢专辑信息
     *
     * @param deviceTypeEnum 系统类型
     * @param deviceId       设备id
     * @param likeCount      返回几条结果数据，默认为3，取之区间[1,50]
     * @return
     */
    public List<Album> guessLikeAlbums(DeviceTypeEnum deviceTypeEnum, String deviceId, int likeCount){
        int MIN_LIKE_COUNT = 1;
        int MAX_LIKE_COUNT = 50;
        int DEFAULT_LIKE_COUNT = 3;
        if(likeCount < MIN_LIKE_COUNT || likeCount > MAX_LIKE_COUNT){
            likeCount = DEFAULT_LIKE_COUNT;
        }
        Map<String, String> params = Maps.newHashMap();
        params.put("device_type", deviceTypeEnum.getCode() + "");
        params.put("device_id", deviceId);
        params.put("like_count", likeCount + "");
        String url = GUESS_LIKE_ALBUMS + "?" + buildParamUrl(params);
        String response = HttpUtil.httpGet(url);
        return JsonBinder.buildNonNullBinder().fromJson(response, new TypeReference<List<Album>>(){
        });
    }

    /**
     * 获取一键听的频道列表
     *
     * @param deviceId 设备id
     */
    public void getOneClickListenChannelList(String deviceId){
        Map<String, String> params = Maps.newHashMap();
        params.put("device_id", deviceId);
        String url = ONE_CLICK_LISTEN_CHANNEL_LIST + "?" + buildParamUrl(params);
        System.out.println(url);
    }
}

enum DeviceTypeEnum{
    IOS(1, "IOS系统"),
    ANDROID(2, "Android系统"),
    WEB(3, "WEB"),
    LINUX(4, "Linux系统"),
    ECOS(5, "ecos系统"),
    QNIX(6, "qnix系统");
    int code;
    String name;

    DeviceTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static DeviceTypeEnum findByCode(int code){
        for(DeviceTypeEnum deviceTypeEnum : DeviceTypeEnum.values()){
            if(deviceTypeEnum.getCode() == code){
                return deviceTypeEnum;
            }
        }
        return ANDROID;
    }
}

enum TagTypeEnum{
    ALBUM_TAG(0, "专辑标签"),
    VOICE_TAG(1, "声音标签");
    int code;
    String name;

    TagTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static TagTypeEnum findByCode(int code){
        for(TagTypeEnum tagTypeEnum : TagTypeEnum.values()){
            if(code == tagTypeEnum.getCode()){
                return tagTypeEnum;
            }
        }
        return ALBUM_TAG;
    }
}

enum SortEnum{
    ASC("asc", "喜马拉雅正序"),
    DESC("desc", "喜马拉雅倒序"),
    TIME_ASC("time_asc", "发布时间升序"),
    TIME_DESC("time_desc", "发布时间降序");
    String code;
    String name;

    SortEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static SortEnum getByCode(String code){
        for(SortEnum sortEnum : SortEnum.values()){
            if(StringUtils.equalsIgnoreCase(sortEnum.getCode(), code)){
                return sortEnum;
            }
        }
        return ASC;
    }
}

enum CalcDimensionEnum{
    HOT(1, "最火"),
    NEW(2, "最新"),
    PLAY(3, "最多播放");
    int code;
    String name;

    CalcDimensionEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public static CalcDimensionEnum findByCode(int code){
        for(CalcDimensionEnum calcDimensionEnum : CalcDimensionEnum.values()){
            if(code == calcDimensionEnum.getCode()){
                return calcDimensionEnum;
            }
        }
        return HOT;
    }
}

enum RankTypeEnum{
    PROGRAMME(1, "节目榜单");
    int code;
    String name;

    RankTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}

enum NetworkTypeEnum{
    ON_LINE(0, "联网播放"),
    OFF_LINE(1, "断网播放");
    int code;
    String name;

    NetworkTypeEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }
}
