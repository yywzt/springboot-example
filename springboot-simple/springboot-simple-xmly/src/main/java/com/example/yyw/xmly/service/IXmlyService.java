package com.example.yyw.xmly.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.yyw.util.DateUtil;
import com.example.yyw.util.HttpUtil;
import com.example.yyw.util.ResultUtil;
import com.example.yyw.xmly.enums.StatusEnum;
import com.example.yyw.xmly.exception.BusinessException;
import com.example.yyw.xmly.mapper.*;
import com.example.yyw.xmly.modal.xmly.XmlyAlbum;
import com.example.yyw.xmly.modal.xmly.XmlyCategory;
import com.example.yyw.xmly.modal.xmly.XmlyTrack;
import com.example.yyw.xmly.response.OpenPushResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 喜马拉雅业务处理
 *
 * @author yanzhitao
 * @date 2018/12/04
 **/
@Slf4j
@Service
@Transactional
public class IXmlyService {

    private static final String OTHER_IF_URL = "http://127.0.0.1:19091";
    /**
     * 分类
     */
    private static final String XMLY_CATEGORY_LIST = OTHER_IF_URL + "/ximalaya/category/list";
    /**
     * 专辑
     */
    private static final String XMLY_ALBUM_LIST = OTHER_IF_URL + "/ximalaya/album/list?";
    /**
     * 专辑下声音碎片
     */
    private static final String XMLY_TRACK_BYALBUM = OTHER_IF_URL + "/ximalaya/track/byAlbum?";

    @Autowired
    private IXmlyAlbumMapper iXmlyAlbumMapper;
    @Autowired
    private IXmlyCategoryMapper iXmlyCategoryMapper;
    @Autowired
    private IXmlyTrackMapper iXmlyTrackMapper;
    @Autowired
    private BasedMapper basedMapper;
    @Autowired
    private RecommendResultMapper recommendResultMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 保存分类
     *
     * @throws BusinessException
     */
    public Object saveCategory() throws BusinessException {
        String responseStr = HttpUtil.httpGet(XMLY_CATEGORY_LIST);
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException("第三方请求结果为空");
        }
        JSONObject jsonObject = JSON.parseObject(responseStr);
        if (!jsonObject.containsKey("data")) {
            throw new BusinessException("第三方请求结果格式不正确，缺少data字段");
        }
        List<XmlyCategory> xmlyCategoryList = jsonObject.getJSONArray("data").toJavaList(XmlyCategory.class);
        if (CollectionUtils.isEmpty(xmlyCategoryList)) {
            throw new BusinessException("分类为空");
        }
        Date currentDate = new Date();
        xmlyCategoryList.forEach(xmlyCategory -> {
            xmlyCategory.setCreateDate(currentDate);
            xmlyCategory.setModifyDate(currentDate);
            xmlyCategory.setStatus(StatusEnum.DEFAULT.getCode());
        });
        iXmlyCategoryMapper.batchSave(xmlyCategoryList);
        return ResultUtil.successResult();
    }

    /**
     * 保存专辑
     *
     * @throws BusinessException
     */

    public Object saveAlbum() throws BusinessException {
        List<XmlyCategory> xmlyCategoryList = findXmlyCategory(StatusEnum.DEFAULT);
        for (XmlyCategory xmlyCategory : xmlyCategoryList) {
            saveAlbumByCategory(xmlyCategory);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("saveAlbum error : {}", e.getMessage());
            }
        }
        return ResultUtil.successResult();
    }

    private void saveAlbumByCategory(XmlyCategory xmlyCategory) throws BusinessException {
        if (null == xmlyCategory) {
            return;
        }
        Long categoryId = xmlyCategory.getOriginId();
        int currentPage = 0;
        int pageSize = 200;
        while (true) {
            StringBuilder url = new StringBuilder(XMLY_ALBUM_LIST);
            url.append("categoryId=" + categoryId);
            url.append("&page=" + currentPage);
            url.append("&size=" + pageSize);
            String responseStr = HttpUtil.httpGet(url.toString());
            if (StringUtils.isBlank(responseStr)) {
                throw new BusinessException("第三方请求结果为空");
            }
            JSONObject jsonObject = JSON.parseObject(responseStr);
            if (!jsonObject.containsKey("list")) {
                throw new BusinessException("第三方请求结果格式不正确，缺少list字段");
            }
            List<XmlyAlbum> xmlyAlbumList = jsonObject.getJSONArray("data").toJavaList(XmlyAlbum.class);
            xmlyAlbumList.forEach(System.out::println);
            Date currentDate = new Date();
            xmlyAlbumList.forEach(xmlyAlbum -> {
                xmlyAlbum.setExtendCategoryOriginId(categoryId);
                xmlyAlbum.setCreateDate(currentDate);
                xmlyAlbum.setModifyDate(currentDate);
                xmlyAlbum.setStatus(StatusEnum.DEFAULT.getCode());
            });
            iXmlyAlbumMapper.batchSave(xmlyAlbumList);
            int totalPages = jsonObject.getIntValue("totalPages");
            currentPage++;
            if (totalPages <= currentPage) {
                break;
            }
        }
        xmlyCategory.setStatus(StatusEnum.SUCCESS.getCode());
        xmlyCategory.setModifyDate(DateUtil.getNowDate());
        iXmlyCategoryMapper.updateStatus(xmlyCategory);
    }

    /**
     * 保存节目碎片
     *
     * @throws BusinessException
     */
    public Object saveTrack() throws BusinessException {
        List<XmlyCategory> xmlyCategoryList = findXmlyCategory(null);
        for (XmlyCategory xmlyCategory : xmlyCategoryList) {
            if (null == xmlyCategory) {
                continue;
            }
            List<XmlyAlbum> xmlyAlbumList = findXmlyAlbumByCategoryAndStatus(xmlyCategory, StatusEnum.DEFAULT);
            if (CollectionUtils.isEmpty(xmlyAlbumList)) {
                continue;
            }
            for (XmlyAlbum xmlyAlbum : xmlyAlbumList) {
                saveTrackByAlbum(xmlyAlbum);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResultUtil.successResult();
    }

    /**
     * 按照专辑，保存节目
     *
     * @param xmlyAlbum
     */
    private void saveTrackByAlbum(XmlyAlbum xmlyAlbum) throws BusinessException {
        if (null == xmlyAlbum) {
            return;
        }
        try {
            Long albumId = xmlyAlbum.getOriginId();
            int currentPage = 0;
            int pageSize = 200;
            while (true) {
                StringBuilder url = new StringBuilder(XMLY_TRACK_BYALBUM);
                url.append("albumId=" + albumId);
                url.append("&page=" + currentPage);
                url.append("&size=" + pageSize);
                System.out.println("====url===" + url.toString());
                String responseStr = HttpUtil.httpGet(url.toString());
                if (StringUtils.isBlank(responseStr)) {
                    throw new BusinessException("第三方请求结果为空");
                }
                JSONObject jsonObject = JSON.parseObject(responseStr);
                if (!jsonObject.containsKey("list")) {
                    throw new BusinessException("第三方请求结果格式不正确，缺少list字段");
                }
                List<XmlyTrack> xmlyTrackList = jsonObject.getJSONArray("data").toJavaList(XmlyTrack.class);
                if (CollectionUtils.isEmpty(xmlyTrackList)) {
                    break;
                }
                Date currentDate = new Date();
                xmlyTrackList.forEach(xmlyTrack -> {
                    xmlyTrack.setExtendCategoryOriginId(xmlyAlbum.getExtendCategoryOriginId());
                    xmlyTrack.setAlbumOriginId(albumId);
                    xmlyTrack.setCreateDate(currentDate);
                    xmlyTrack.setModifyDate(currentDate);
                    xmlyTrack.setStatus(StatusEnum.DEFAULT.getCode());
                });
                iXmlyTrackMapper.batchSave(xmlyTrackList);
                int totalPages = jsonObject.getIntValue("totalPages");
                currentPage++;
                if (totalPages <= currentPage) {
                    break;
                }
            }
            xmlyAlbum.setStatus(StatusEnum.SUCCESS.getCode());
            iXmlyAlbumMapper.updateStatus(xmlyAlbum);
        } catch (Exception e) {
            log.error("Exception when save track by album", e);
        }
    }

    /**
     * 查询可用的喜马拉雅分类集合
     *
     * @return
     * @throws BusinessException
     */
    private List<XmlyCategory> findXmlyCategory(StatusEnum statusEnum) throws BusinessException {
        Map<String, Object> params = new HashMap<>();
        if (null != statusEnum) {
            params.put("status", statusEnum.getCode());
        }
        List<XmlyCategory> xmlyCategoryList = iXmlyCategoryMapper.findByCondition(params);
        return xmlyCategoryList;
    }

    /**
     * 根据分类，获取专辑集合
     *
     * @param xmlyCategory 分类对象
     * @return 专辑集合
     */
    private List<XmlyAlbum> findXmlyAlbumByCategory(XmlyCategory xmlyCategory) {
        if (null == xmlyCategory) {
            return new ArrayList<>();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("extendCategoryOriginId", xmlyCategory.getOriginId());
        params.put("status", StatusEnum.DEFAULT.getCode());
        List<XmlyAlbum> xmlyAlbumList = iXmlyAlbumMapper.findByCondition(params);
        return xmlyAlbumList;
    }

    /**
     * 根据分类，获取专辑集合
     *
     * @param xmlyCategory 分类对象
     * @return 专辑集合
     */
    private List<XmlyAlbum> findXmlyAlbumByCategoryAndStatus(XmlyCategory xmlyCategory, StatusEnum statusEnum) {
        if (null == xmlyCategory) {
            return new ArrayList<>();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("extendCategoryOriginId", xmlyCategory.getOriginId());
        params.put("status", statusEnum.getCode());
        List<XmlyAlbum> xmlyAlbumList = iXmlyAlbumMapper.findByCondition(params);
        return xmlyAlbumList;
    }

    //源表名
    private static final String XMLY_CATEGORY_BACK = "xmly_category";
    private static final String XMLY_ALBUM_BACK = "xmly_album";
    private static final String XMLY_TRACK_BACK = "xmly_track";

    //中间表名
    private static final String XMLY_CATEGORY_BACK_TMP = "xmly_category_tmp";
    private static final String XMLY_ALBUM_BACK_TMP = "xmly_album_tmp";
    private static final String XMLY_TRACK_BACK_TMP = "xmly_track_tmp";

    //备份表名
    private static final String XMLY_CATEGORY_BACK_BAK = "xmly_category_bak";
    private static final String XMLY_ALBUM_BACK_BAK = "xmly_album_bak";
    private static final String XMLY_TRACK_BACK_BAK = "xmly_track_bak";

    /**
     * 分类全量更新
     * 数据存入中间表
     *
     * @param tableName 中间表名
     * @throws BusinessException
     */
    private void saveAllCategory(String tableName) throws BusinessException {
        String url = OTHER_IF_URL + "/ximalaya/category/list";
        String responseStr = HttpUtil.httpGet(url);
        if (StringUtils.isBlank(responseStr)) {
            log.error("分类-第三方请求结果为空");
            throw new BusinessException("分类-第三方请求结果为空");
        }
        JSONObject jsonObject = JSON.parseObject(responseStr);
        if (!jsonObject.containsKey("data")) {
            log.error("分类-第三方请求结果格式不正确，缺少list字段");
            throw new BusinessException("分类-第三方请求结果格式不正确，缺少data字段");
        }
        List<XmlyCategory> xmlyCategoryList = jsonObject.getJSONArray("data").toJavaList(XmlyCategory.class);
        if (CollectionUtils.isEmpty(xmlyCategoryList)) {
            log.error("分类为空");
            throw new BusinessException("分类为空");
        }
        Date currentDate = new Date();
        xmlyCategoryList.forEach(xmlyCategory -> {
            xmlyCategory.setCreateDate(currentDate);
            xmlyCategory.setModifyDate(currentDate);
            xmlyCategory.setStatus(StatusEnum.DEFAULT.getCode());
        });
        if (CollectionUtils.isNotEmpty(xmlyCategoryList)) {
            basedMapper.batchSaveCategorys(xmlyCategoryList, tableName);
        }
    }

    /**
     * 专辑全量更新
     * 数据存入中间表
     *
     * @param tableName 中间表名
     * @throws BusinessException
     */
    private void saveAllAlbum(String tableName) throws BusinessException {
        List<XmlyCategory> xmlyCategoryList = findXmlyCategoryByTableName(StatusEnum.DEFAULT);
        for (XmlyCategory xmlyCategory : xmlyCategoryList) {
            saveAllAlbumByCategory(xmlyCategory, tableName);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveAllAlbumByCategory(XmlyCategory xmlyCategory, String tableName) throws BusinessException {
        if (null == xmlyCategory) {
            return;
        }
        Long categoryId = xmlyCategory.getOriginId();
        int currentPage = 0;
        int pageSize = 200;
        while (true) {
            StringBuilder url = new StringBuilder(OTHER_IF_URL + "/ximalaya/album/list?");
            url.append("categoryId=" + categoryId);
            url.append("&page=" + currentPage);
            url.append("&size=" + pageSize);
            String responseStr = HttpUtil.httpGet(url.toString());
            if (StringUtils.isBlank(responseStr)) {
                log.error("专辑-第三方请求结果为空");
                throw new BusinessException("专辑-第三方请求结果为空");
            }
            JSONObject jsonObject = JSON.parseObject(responseStr);
            if (!jsonObject.containsKey("list")) {
                log.error("专辑-第三方请求结果格式不正确，缺少list字段");
                throw new BusinessException("专辑-第三方请求结果格式不正确，缺少list字段");
            }
            List<XmlyAlbum> xmlyAlbumList = jsonObject.getJSONArray("list").toJavaList(XmlyAlbum.class);
            xmlyAlbumList.forEach(System.out::println);
            Date currentDate = new Date();
            xmlyAlbumList.forEach(xmlyAlbum -> {
                xmlyAlbum.setExtendCategoryOriginId(categoryId);
                xmlyAlbum.setCreateDate(currentDate);
                xmlyAlbum.setModifyDate(currentDate);
                xmlyAlbum.setStatus(StatusEnum.DEFAULT.getCode());
            });
            if (CollectionUtils.isNotEmpty(xmlyAlbumList)) {
                basedMapper.batchSaveAlbums(xmlyAlbumList, tableName);
            }
            int totalPages = jsonObject.getIntValue("totalPages");
            currentPage++;
            if (totalPages <= currentPage) {
                break;
            }
        }
        xmlyCategory.setStatus(StatusEnum.SUCCESS.getCode());
        xmlyCategory.setModifyDate(DateUtil.getNowDate());
        basedMapper.updateCategoryStatus(xmlyCategory, XMLY_CATEGORY_BACK_TMP);
    }

    /**
     * 节目片段全量更新
     * 数据存入中间表
     *
     * @param tableName 中间表名
     * @throws BusinessException
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveAllTrack(String tableName) throws BusinessException {
        List<XmlyCategory> xmlyCategoryList = findXmlyCategoryByTableName(null);
        for (XmlyCategory xmlyCategory : xmlyCategoryList) {
            if (null == xmlyCategory) {
                continue;
            }
            List<XmlyAlbum> xmlyAlbumList = findXmlyAlbumByCategoryAndStatusByTableName(xmlyCategory, StatusEnum.DEFAULT);
            if (CollectionUtils.isEmpty(xmlyAlbumList)) {
                continue;
            }
            for (XmlyAlbum xmlyAlbum : xmlyAlbumList) {
                saveAllTrackByAlbum(xmlyAlbum, tableName);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveAllTrackByAlbum(XmlyAlbum xmlyAlbum, String tableName) throws BusinessException {
        if (null == xmlyAlbum) {
            return;
        }
        try {
            Long albumId = xmlyAlbum.getOriginId();
            int currentPage = 0;
            int pageSize = 200;
            while (true) {
                StringBuilder url = new StringBuilder(OTHER_IF_URL + "/ximalaya/track/byAlbum?");
                url.append("albumId=" + albumId);
                url.append("&page=" + currentPage);
                url.append("&size=" + pageSize);
                System.out.println("====url===" + url.toString());
                String responseStr = HttpUtil.httpGet(url.toString());
                if (StringUtils.isBlank(responseStr)) {
                    log.error("声音-第三方请求结果为空");
                    throw new BusinessException("声音-第三方请求结果为空");
                }
                JSONObject jsonObject = JSON.parseObject(responseStr);
                if (!jsonObject.containsKey("list")) {
                    log.error("声音-第三方请求结果格式不正确，缺少list字段");
                    throw new BusinessException("声音-第三方请求结果格式不正确，缺少list字段");
                }
                List<XmlyTrack> xmlyTrackList = jsonObject.getJSONArray("list").toJavaList(XmlyTrack.class);
                if (CollectionUtils.isEmpty(xmlyTrackList)) {
                    break;
                }
                Date currentDate = new Date();
                xmlyTrackList.forEach(xmlyTrack -> {
                    xmlyTrack.setExtendCategoryOriginId(xmlyAlbum.getExtendCategoryOriginId());
                    xmlyTrack.setAlbumOriginId(albumId);
                    xmlyTrack.setCreateDate(currentDate);
                    xmlyTrack.setModifyDate(currentDate);
                    xmlyTrack.setStatus(StatusEnum.DEFAULT.getCode());
                });
                if (CollectionUtils.isNotEmpty(xmlyTrackList)) {
                    basedMapper.batchSaveTracks(xmlyTrackList, tableName);
                }
                int totalPages = jsonObject.getIntValue("totalPages");
                currentPage++;
                if (totalPages <= currentPage) {
                    break;
                }
            }
            xmlyAlbum.setStatus(StatusEnum.SUCCESS.getCode());
            xmlyAlbum.setModifyDate(DateUtil.getNowDate());
            basedMapper.updateAlbumStatus(xmlyAlbum, XMLY_ALBUM_BACK_TMP);
        } catch (Exception e) {
            log.error("Exception when save track by album", e);
            log.error("Exception when save track by album {}", xmlyAlbum.getOriginId());
        }
    }

    /**
     * 节目增量更新，10分钟前-当前时间
     *
     * @throws BusinessException
     */
    public void saveIncrementTrack() throws BusinessException {
        Date endTime = new Date();
        Date startTime = DateUtil.addMinute(endTime, -10);
        long start_time = startTime.getTime();
        long end_time = endTime.getTime();
        List<XmlyCategory> xmlyCategoryList = findXmlyCategory(null);
        for (XmlyCategory xmlyCategory : xmlyCategoryList) {
            if (null == xmlyCategory) {
                continue;
            }
            List<XmlyAlbum> xmlyAlbumList = findXmlyAlbumByCategoryAndStatus(xmlyCategory, StatusEnum.SUCCESS);
            if (CollectionUtils.isEmpty(xmlyAlbumList)) {
                continue;
            }
            for (XmlyAlbum xmlyAlbum : xmlyAlbumList) {
                saveIncrementTrack(xmlyAlbum, start_time, end_time);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据时间区间分页获取增量节目信息，并保存
     * 时间区间必须小于等于10分钟
     *
     * @param xmlyAlbum
     * @param start_time 开始时间的Unix时间戳毫秒数
     * @param end_time   结束时间，Unix时间戳毫秒数
     * @throws BusinessException
     */
    private void saveIncrementTrack(XmlyAlbum xmlyAlbum, long start_time, long end_time) throws BusinessException {
        if (null == xmlyAlbum) {
            return;
        }
        try {
            if (null == xmlyAlbum) {
                return;
            }
            Long albumId = xmlyAlbum.getOriginId();
            int currentPage = 1;
            int pageSize = 200;
            while (true) {
                StringBuilder url = new StringBuilder(OTHER_IF_URL + "/ximalaya/album/getIncrementTracks?");
                url.append("albumId=" + albumId);
                url.append("&startDate=" + start_time);
                url.append("&endDate=" + end_time);
                url.append("&page=" + currentPage);
                url.append("&size=" + pageSize);
                String responseStr = HttpUtil.httpGet(url.toString());
                if (StringUtils.isBlank(responseStr)) {
                    throw new BusinessException("第三方请求结果为空");
                }
                JSONObject jsonObject = JSON.parseObject(responseStr);
                if (!jsonObject.containsKey("list")) {
                    throw new BusinessException("第三方请求结果格式不正确，缺少list字段");
                }
                List<XmlyTrack> xmlyTrackList = jsonObject.getJSONArray("list").toJavaList(XmlyTrack.class);
                if (CollectionUtils.isEmpty(xmlyTrackList)) {
                    break;
                }
                Date currentDate = new Date();
                xmlyTrackList.forEach(xmlyTrack -> {
                    xmlyTrack.setExtendCategoryOriginId(xmlyAlbum.getExtendCategoryOriginId());
                    xmlyTrack.setAlbumOriginId(albumId);
                    xmlyTrack.setCreateDate(currentDate);
                    xmlyTrack.setModifyDate(currentDate);
                    xmlyTrack.setStatus(StatusEnum.DEFAULT.getCode());
                });
                if (CollectionUtils.isNotEmpty(xmlyTrackList)) {
                    iXmlyTrackMapper.batchSave(xmlyTrackList);
                }
                int totalPages = jsonObject.getIntValue("totalPages");
                currentPage++;
                if (totalPages <= currentPage) {
                    break;
                }
            }
        } catch (BusinessException e) {
            log.error("Exception when save increment track by album", e);
        }
    }

    /**
     * 增量更新热门专辑
     * 更新策略：拉取热门专辑，库中已存在的热门专辑的保留，不存在的热门专辑入库（status=0）
     * 更新完热门专辑后，根据status=0的专辑id去获取对应全量节目片段
     *
     * @throws BusinessException
     */
    public void saveIncrementAlbum() throws BusinessException {
        List<XmlyCategory> xmlyCategoryList = findXmlyCategory(null);
        for (XmlyCategory xmlyCategory : xmlyCategoryList) {
            saveIncrementAlbumByCategory(xmlyCategory);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveIncrementAlbumByCategory(XmlyCategory xmlyCategory) throws BusinessException {
        if (null == xmlyCategory) {
            return;
        }
        Long categoryId = xmlyCategory.getOriginId();
        int currentPage = 0;
        int pageSize = 200;

        //根据分类获取库中已存在的专辑id
        Map<String, Object> params = new HashMap<>();
        params.put("extendCategoryOriginId", xmlyCategory.getOriginId());
        List<Long> originIdList = iXmlyAlbumMapper.findByCondition(params).stream().map(XmlyAlbum::getOriginId).collect(Collectors.toList());

        while (true) {
            StringBuilder url = new StringBuilder(OTHER_IF_URL + "/ximalaya/album/list?");
            url.append("categoryId=" + categoryId);
            url.append("&page=" + currentPage);
            url.append("&size=" + pageSize);
            String responseStr = HttpUtil.httpGet(url.toString());
            if (StringUtils.isBlank(responseStr)) {
                throw new BusinessException("第三方请求结果为空");
            }
            JSONObject jsonObject = JSON.parseObject(responseStr);
            if (!jsonObject.containsKey("list")) {
                throw new BusinessException("第三方请求结果格式不正确，缺少list字段");
            }
            List<XmlyAlbum> xmlyAlbumList = jsonObject.getJSONArray("list").toJavaList(XmlyAlbum.class);
            //剔除库中已存在的热门专辑
            List<XmlyAlbum> newXmlyAlbumList = xmlyAlbumList.stream().filter(xmlyAlbum -> !originIdList.contains(xmlyAlbum.getOriginId())).collect(Collectors.toList());
            Date currentDate = new Date();
            newXmlyAlbumList.forEach(xmlyAlbum -> {
                xmlyAlbum.setExtendCategoryOriginId(categoryId);
                xmlyAlbum.setCreateDate(currentDate);
                xmlyAlbum.setModifyDate(currentDate);
                xmlyAlbum.setStatus(StatusEnum.DEFAULT.getCode());
            });
            if (CollectionUtils.isNotEmpty(newXmlyAlbumList)) {
                iXmlyAlbumMapper.batchSave(newXmlyAlbumList);
            }
            int totalPages = jsonObject.getIntValue("totalPages");
            currentPage++;
            if (totalPages <= currentPage) {
                break;
            }
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void saveAll() throws BusinessException {
        createTmpTable(XMLY_CATEGORY_BACK_TMP, XMLY_CATEGORY_BACK);
        saveAllCategory(XMLY_CATEGORY_BACK_TMP);

        createTmpTable(XMLY_ALBUM_BACK_TMP, XMLY_ALBUM_BACK);
        saveAllAlbum(XMLY_ALBUM_BACK_TMP);

        createTmpTable(XMLY_TRACK_BACK_TMP, XMLY_TRACK_BACK);
        saveAllTrack(XMLY_TRACK_BACK_TMP);

        alterTableName();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private void createTmpTable(String tmpTableName, String sourceTableName) {
        basedMapper.dropTable(tmpTableName);
        basedMapper.createTable(tmpTableName, sourceTableName);
    }

    /**
     * 源表 ==> bak
     * tmp ==>源表名
     */
    private void alterTableName() {
        //分类表
        //源表名重命名为备份表名
        basedMapper.dropTable(XMLY_CATEGORY_BACK_BAK);
        basedMapper.alterTableName(XMLY_CATEGORY_BACK, XMLY_CATEGORY_BACK_BAK);
        //中间表名重命名为源表名
        basedMapper.dropTable(XMLY_CATEGORY_BACK);
        basedMapper.alterTableName(XMLY_CATEGORY_BACK_TMP, XMLY_CATEGORY_BACK);

        //专辑表
        //源表名重命名为备份表名
        basedMapper.dropTable(XMLY_ALBUM_BACK_BAK);
        basedMapper.alterTableName(XMLY_ALBUM_BACK, XMLY_ALBUM_BACK_BAK);
        //中间表名重命名为源表名
        basedMapper.dropTable(XMLY_ALBUM_BACK);
        basedMapper.alterTableName(XMLY_ALBUM_BACK_TMP, XMLY_ALBUM_BACK);

        //声音表
        //源表名重命名为备份表名
        basedMapper.dropTable(XMLY_TRACK_BACK_BAK);
        basedMapper.alterTableName(XMLY_TRACK_BACK, XMLY_TRACK_BACK_BAK);
        //中间表名重命名为源表名
        basedMapper.dropTable(XMLY_TRACK_BACK);
        basedMapper.alterTableName(XMLY_TRACK_BACK_TMP, XMLY_TRACK_BACK);
    }

    /**
     * 从tmp表中查询可用的喜马拉雅分类集合
     *
     * @return
     * @throws BusinessException
     */
    private List<XmlyCategory> findXmlyCategoryByTableName(StatusEnum statusEnum) throws BusinessException {
        Map<String, Object> params = new HashMap<>();
        if (null != statusEnum) {
            params.put("status", statusEnum.getCode());
        }
        List<XmlyCategory> xmlyCategoryList = basedMapper.findCategoryByCondition(params, XMLY_CATEGORY_BACK_TMP);
        return xmlyCategoryList;
    }

    /**
     * 根据分类，从tmp表中获取专辑集合
     *
     * @param xmlyCategory 分类对象
     * @return 专辑集合
     */
    private List<XmlyAlbum> findXmlyAlbumByCategoryAndStatusByTableName(XmlyCategory xmlyCategory, StatusEnum statusEnum) {
        if (null == xmlyCategory) {
            return new ArrayList<>();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("extendCategoryOriginId", xmlyCategory.getOriginId());
        params.put("status", statusEnum.getCode());
        List<XmlyAlbum> xmlyAlbumList = basedMapper.findAlbumByCondition(params, XMLY_ALBUM_BACK_TMP);
        return xmlyAlbumList;
    }

    /**
     * 实时推送专辑/声音的上下架状态
     *
     * @param push_type             Int	是	推送内容类型：1-专辑，2-声音
     * @param id                    Int	是	推送内容ID，即专辑ID（push_type为1时）或声音ID（push_type为2时）
     * @param subordinated_album_id Int	否	如果推送内容类型为声音时，有此字段，表示声音所属专辑ID
     * @param is_paid               Bool	否	是否是付费内容：true-付费，false-免费。没有该参数时默认为免费内容
     * @param updated_at            Int	是	业务发生时间（即发生上下架事件的时刻），Unix毫秒数时间戳
     * @param is_online             Bool	是	内容上下架状态：true-上架，false-下架
     * @param offline_reason_type   Int	是	下架原因： 0-无此属性，1-运营/主播下架内容，2-版权变更导致内容不再输出
     * @param nonce                 String	是	随机字符串
     * @return json 字段为：
     * code    Int	推送结果：0-成功，1-失败
     * message	String	可选，失败时为出错描述
     * source	String	必填，唯一标识推送接口提供方来源，需要合作方和喜马拉雅共同约定
     */
    public OpenPushResponse openPush(Integer push_type, Integer id, Integer subordinated_album_id, Boolean is_paid, Long updated_at,
                                     Boolean is_online, Integer offline_reason_type, String nonce, Long timestamp) {
        switch (push_type) {
            case 1:
                openPushAlbum(id, updated_at, is_online);
                break;
            case 2:
                openPushTrack(id, subordinated_album_id, updated_at, is_online);
                break;
        }
        return OpenPushResponse.success();
    }

    public Map<String, Object> buildParams(String app_key, Integer push_type, Integer id, Integer subordinated_album_id, Boolean is_paid,
                                           Long updated_at, Boolean is_online, Integer offline_reason_type, String nonce, Long timestamp) {
        Map<String, Object> params = new HashMap<>();
        params.put("app_key", app_key);
        params.put("push_type", push_type);
        params.put("id", id);
        params.put("subordinated_album_id", subordinated_album_id);
        params.put("is_paid", is_paid);
        params.put("updated_at", updated_at);
        params.put("is_online", is_online);
        params.put("offline_reason_type", offline_reason_type);
        params.put("nonce", nonce);
        params.put("timestamp", timestamp);
        return params;
    }

    private static final String APP_KEY = "ba39f971bcb35d12afe892ab20be0e14";
    private static final String APP_SECRET = "4749ba4fd2931e96744688aa1714b0cd";

    /**
     * 对参数进行校验
     *
     * @param params 包含sig
     * @return
     */
    public String verifySign(Map<String, Object> params) {
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuffer sign_param = new StringBuffer();

        for (String key : keys) {
            Object o = params.get(key);
            if (o != null) {
                sign_param.append(key).append("=").append(o).append("&");
            }
        }
        sign_param.append("app_secret=").append(APP_SECRET);
        String sign = DigestUtils.md5DigestAsHex(sign_param.toString().getBytes());
        log.info("sign_params : {},sign : {}", sign_param.toString(), sign);
        return sign;
    }

    /**
     * @param id         推送内容ID，即专辑ID（push_type为1时）或声音ID（push_type为2时）
     * @param updated_at 业务发生时间（即发生上下架事件的时刻），Unix毫秒数时间戳
     * @param is_online  内容上下架状态：true-上架，false-下架
     */
    private void openPushAlbum(Integer id, Long updated_at, Boolean is_online) {
        int status = is_online ? StatusEnum.DEFAULT.getCode() : StatusEnum.HISTORY.getCode();
        int i = iXmlyAlbumMapper.upOrLow(id, new Date(updated_at), status);
        //获取专辑下声音id集合
        Map<Long, List<XmlyTrack>> trackIds = iXmlyTrackMapper.findOriginIdAndExtendCategoryOriginIdByCondition(new HashMap<String, Object>() {{
            put("albumOriginId", id);
        }}).stream().collect(Collectors.groupingBy(XmlyTrack::getExtendCategoryOriginId));

        if (trackIds != null && trackIds.size() > 0) {
            for (Map.Entry<Long, List<XmlyTrack>> listEntry : trackIds.entrySet()) {
                Long extendCategoryOriginId = listEntry.getKey();
                List<Long> originIds = listEntry.getValue().stream().map(XmlyTrack::getOriginId).collect(Collectors.toList());
                String[] originIdArray = originIds.stream().map(aLong -> aLong.toString()).toArray(String[]::new);
                //声音上下架
                iXmlyTrackMapper.batchIpOrLow(originIds, new Date(updated_at), status);
                //rec_result数据上下架
                recommendResultMapper.updateStatusByItemId(new HashMap<String, Object>() {{
                    put("originIds", originIds);
                    put("itemType", "3");
                    put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                    put("status", status);
                }});

                //更新redis缓存，专辑对应声音碎片id需要进行remove操作
                String key = XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_TO_TRACK_ID_CACHE_PREFIX + extendCategoryOriginId.toString();
                if (is_online) {
                    stringRedisTemplate.opsForSet().add(key, originIdArray);
                } else {
                    stringRedisTemplate.opsForSet().remove(key, originIdArray);
                }
            }
        }

    }

    public static final String XIMALAYA_CACHE_COMMON_PREFIX = "XIMALAYA:";
    public static final String XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_TO_TRACK_ID_CACHE_PREFIX = XIMALAYA_CACHE_COMMON_PREFIX + "EOCI_TI:";
    public static final String XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_LIST_CACHE_PREFIX = XIMALAYA_CACHE_COMMON_PREFIX + "EOCI_LIST";

    /**
     * @param id                    推送内容ID，即专辑ID（push_type为1时）或声音ID（push_type为2时）
     * @param subordinated_album_id 如果推送内容类型为声音时，有此字段，表示声音所属专辑ID
     * @param updated_at            业务发生时间（即发生上下架事件的时刻），Unix毫秒数时间戳
     * @param is_online             内容上下架状态：true-上架，false-下架
     */
    private void openPushTrack(Integer id, Integer subordinated_album_id, Long updated_at, Boolean is_online) {
        List<XmlyTrack> xmlyTrackList = iXmlyTrackMapper.findOriginIdAndExtendCategoryOriginIdByCondition(new HashMap<String, Object>() {{
            put("originId", id);
        }});
        if (CollectionUtils.isNotEmpty(xmlyTrackList)) {
            xmlyTrackList.stream().forEach(xmlyTrack -> {
                int status = is_online ? StatusEnum.DEFAULT.getCode() : StatusEnum.HISTORY.getCode();
                //声音上下架
                iXmlyTrackMapper.ipOrLow(id, new Date(updated_at), status);
                //rec_result数据上下架
                recommendResultMapper.updateStatusByItemId(new HashMap<String, Object>() {{
                    put("originId", id);
                    put("itemType", "3");
                    put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                    put("status", status);
                }});

                //更新redis缓存，专辑对应声音碎片id需要进行remove操作
                String key = XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_TO_TRACK_ID_CACHE_PREFIX + xmlyTrack.getExtendCategoryOriginId().toString();
                if (is_online) {
                    stringRedisTemplate.opsForSet().add(key, xmlyTrack.getOriginId().toString());
                } else {
                    stringRedisTemplate.opsForSet().remove(key, xmlyTrack.getOriginId().toString());
                }
            });
        }
    }

    //tmp缓存key
    private static final String TMP = "TMP";
    private static final String RISK = ":";
    public static final String TMP_XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_TO_TRACK_ID_CACHE_PREFIX = XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_TO_TRACK_ID_CACHE_PREFIX + TMP + RISK;
    public static final String TMP_XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_LIST_CACHE_PREFIX = XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_LIST_CACHE_PREFIX + RISK + TMP;


    public void buildXimalayaTrackCacheTmp() {
        Long startTime = System.currentTimeMillis();

        List<XmlyTrack> xmlyTrackList = iXmlyTrackMapper.findOriginIdAndExtendCategoryOriginIdByCondition(new HashMap<String, Object>() {{
            put("status", StatusEnum.DEFAULT.getCode());
        }});
        xmlyTrackList.stream()
                .collect(Collectors.groupingBy(XmlyTrack::getExtendCategoryOriginId, Collectors.toList()))
                .entrySet()
                .forEach(entry -> {
                    String extendCategoryOriginId = entry.getKey().toString();
                    List<String> xmlyTrackOriginIds = entry.getValue().stream()
                            .map(xmlyTrack -> xmlyTrack.getOriginId().toString())
                            .collect(Collectors.toList());
                    String cacheKey = TMP_XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_TO_TRACK_ID_CACHE_PREFIX + extendCategoryOriginId;
                    if (CollectionUtils.isNotEmpty(xmlyTrackOriginIds)) {
                        stringRedisTemplate.opsForSet()
                                .add(cacheKey, xmlyTrackOriginIds.toArray(new String[0]));
                    }
                });
        List<String> xmlyExtendCategoryOriginIdList = iXmlyTrackMapper.findExtendCategoryOriginIdByCondition(new HashedMap() {{
            put("status", StatusEnum.DEFAULT.getCode());
        }});
        stringRedisTemplate.opsForSet().add(TMP_XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_LIST_CACHE_PREFIX, xmlyExtendCategoryOriginIdList.stream().toArray(String[]::new));
        log.info("=========build ximalaya tmp cache use time=========" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public void buildXimalayaTrackCache() {
        Long startTime = System.currentTimeMillis();
        List<String> xmlyExtendCategoryOriginIdList = iXmlyTrackMapper.findExtendCategoryOriginIdByCondition(new HashedMap() {{
            put("status", StatusEnum.DEFAULT.getCode());
        }});
        xmlyExtendCategoryOriginIdList.stream().forEach(extendCategoryOriginId -> {
            List<XmlyTrack> xmlyTrackList = iXmlyTrackMapper.findOriginIdAndExtendCategoryOriginIdByCondition(new HashMap<String, Object>() {{
                put("status", StatusEnum.DEFAULT.getCode());
                put("extendCategoryOriginId", extendCategoryOriginId);
            }});
            List<String> originIds = xmlyTrackList.stream().map(xmlyTrack -> xmlyTrack.getOriginId().toString()).collect(Collectors.toList());
            String cacheKey = XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_TO_TRACK_ID_CACHE_PREFIX + extendCategoryOriginId;
            if (CollectionUtils.isNotEmpty(originIds)) {
                stringRedisTemplate.opsForSet()
                        .add(cacheKey, originIds.toArray(new String[0]));
            }
        });

        stringRedisTemplate.opsForSet()
                .add(XIMALAYA_EXTEND_ORIGIN_CATEGORY_ID_LIST_CACHE_PREFIX, xmlyExtendCategoryOriginIdList.toArray(new String[0]));
        log.info("=========build ximalaya cache use time=========" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
