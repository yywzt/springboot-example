package com.example.yyw.xmly.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.yyw.util.DateUtil;
import com.example.yyw.util.HttpUtil;
import com.example.yyw.util.ResultUtil;
import com.example.yyw.xmly.enums.StatusEnum;
import com.example.yyw.xmly.exception.BusinessException;
import com.example.yyw.xmly.mapper.BasedMapper;
import com.example.yyw.xmly.mapper.IXmlyAlbumMapper;
import com.example.yyw.xmly.mapper.IXmlyCategoryMapper;
import com.example.yyw.xmly.mapper.IXmlyTrackMapper;
import com.example.yyw.xmly.modal.xmly.XmlyAlbum;
import com.example.yyw.xmly.modal.xmly.XmlyCategory;
import com.example.yyw.xmly.modal.xmly.XmlyTrack;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    /** 分类 */
    private static final String XMLY_CATEGORY_LIST = OTHER_IF_URL + "/ximalaya/category/list";
    /** 专辑 */
    private static final String XMLY_ALBUM_LIST = OTHER_IF_URL + "/ximalaya/album/list?";
    /** 专辑下声音碎片 */
    private static final String XMLY_TRACK_BYALBUM = OTHER_IF_URL + "/ximalaya/track/byAlbum?";

    @Autowired
    private IXmlyAlbumMapper iXmlyAlbumMapper;
    @Autowired
    private IXmlyCategoryMapper iXmlyCategoryMapper;
    @Autowired
    private IXmlyTrackMapper iXmlyTrackMapper;
    @Autowired
    private BasedMapper basedMapper;
    
    /**
     * 保存分类
     *
     * @throws BusinessException
     */
    public Object saveCategory() throws BusinessException {
        String responseStr = HttpUtil.httpGet(XMLY_CATEGORY_LIST);
        if(StringUtils.isBlank(responseStr)){
            throw new BusinessException("第三方请求结果为空");
        }
        JSONObject jsonObject = JSON.parseObject(responseStr);
        if(!jsonObject.containsKey("data")){
            throw new BusinessException("第三方请求结果格式不正确，缺少data字段");
        }
        List<XmlyCategory> xmlyCategoryList = jsonObject.getJSONArray("data").toJavaList(XmlyCategory.class);
        if(CollectionUtils.isEmpty(xmlyCategoryList)){
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

    public Object saveAlbum() throws BusinessException{
        List<XmlyCategory> xmlyCategoryList = findXmlyCategory(StatusEnum.DEFAULT);
        for(XmlyCategory xmlyCategory : xmlyCategoryList){
            saveAlbumByCategory(xmlyCategory);
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
                log.error("saveAlbum error : {}", e.getMessage());
            }
        }
        return ResultUtil.successResult();
    }

    private void saveAlbumByCategory(XmlyCategory xmlyCategory) throws BusinessException{
        if(null == xmlyCategory){
            return;
        }
        Long categoryId = xmlyCategory.getOriginId();
        int currentPage = 0;
        int pageSize = 200;
        while(true){
            StringBuilder url = new StringBuilder(XMLY_ALBUM_LIST);
            url.append("categoryId=" + categoryId);
            url.append("&page=" + currentPage);
            url.append("&size=" + pageSize);
            String responseStr = HttpUtil.httpGet(url.toString());
            if(StringUtils.isBlank(responseStr)){
                throw new BusinessException("第三方请求结果为空");
            }
            JSONObject jsonObject = JSON.parseObject(responseStr);
            if(!jsonObject.containsKey("list")){
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
            if(totalPages <= currentPage){
                break;
            }
        }
        xmlyCategory.setStatus(StatusEnum.SUCCESS.getCode());
        iXmlyCategoryMapper.updateStatus(xmlyCategory);
    }

    /**
     * 保存节目碎片
     *
     * @throws BusinessException
     */
    public Object saveTrack() throws BusinessException{
        List<XmlyCategory> xmlyCategoryList = findXmlyCategory(null);
        for(XmlyCategory xmlyCategory : xmlyCategoryList){
            if(null == xmlyCategory){
                continue;
            }
            List<XmlyAlbum> xmlyAlbumList = findXmlyAlbumByCategory(xmlyCategory);
            if(CollectionUtils.isEmpty(xmlyAlbumList)){
                continue;
            }
            for(XmlyAlbum xmlyAlbum : xmlyAlbumList){
                if(xmlyAlbum.getStatus().intValue() == StatusEnum.DEFAULT.getCode()){
                    saveTrackByAlbum(xmlyAlbum);
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
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
    private void saveTrackByAlbum(XmlyAlbum xmlyAlbum) throws BusinessException{
        if(null == xmlyAlbum){
            return;
        }
        try{
            Long albumId = xmlyAlbum.getOriginId();
            int currentPage = 0;
            int pageSize = 200;
            while(true){
                StringBuilder url = new StringBuilder(XMLY_TRACK_BYALBUM);
                url.append("albumId=" + albumId);
                url.append("&page=" + currentPage);
                url.append("&size=" + pageSize);
                System.out.println("====url===" + url.toString());
                String responseStr = HttpUtil.httpGet(url.toString());
                if(StringUtils.isBlank(responseStr)){
                    throw new BusinessException("第三方请求结果为空");
                }
                JSONObject jsonObject = JSON.parseObject(responseStr);
                if(!jsonObject.containsKey("list")){
                    throw new BusinessException("第三方请求结果格式不正确，缺少list字段");
                }
                List<XmlyTrack> xmlyTrackList = jsonObject.getJSONArray("data").toJavaList(XmlyTrack.class);
                if(CollectionUtils.isEmpty(xmlyTrackList)){
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
                if(totalPages <= currentPage){
                    break;
                }
            }
            xmlyAlbum.setStatus(StatusEnum.SUCCESS.getCode());
            iXmlyAlbumMapper.updateStatus(xmlyAlbum);
        }catch(Exception e){
            log.error("Exception when save track by album", e);
        }
    }

    /**
     * 查询可用的喜马拉雅分类集合
     *
     * @return
     * @throws BusinessException
     */
    private List<XmlyCategory> findXmlyCategory(StatusEnum statusEnum) throws BusinessException{
        Map<String, Object> params = new HashMap<>();
        if(null != statusEnum){
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
    private List<XmlyAlbum> findXmlyAlbumByCategory(XmlyCategory xmlyCategory){
        if(null == xmlyCategory){
            return new ArrayList<>();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("extendCategoryOriginId", xmlyCategory.getOriginId());
        params.put("status", StatusEnum.DEFAULT.getCode());
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
     * 获取分类存入中间表，完成之后原表做备份
     *
     * @throws BusinessException
     */
    public void saveAllXmlyCategory() throws BusinessException {
        basedMapper.dropTable(XMLY_CATEGORY_BACK_TMP);
        basedMapper.createTable(XMLY_CATEGORY_BACK_TMP, XMLY_CATEGORY_BACK);
        try {
            saveAllCategory(XMLY_CATEGORY_BACK_TMP);
        } finally {
            //源表名重命名为备份表名
            basedMapper.dropTable(XMLY_CATEGORY_BACK_BAK);
            basedMapper.alterTableName(XMLY_CATEGORY_BACK, XMLY_CATEGORY_BACK_BAK);
            //中间表名重命名为源表名
            basedMapper.dropTable(XMLY_CATEGORY_BACK);
            basedMapper.alterTableName(XMLY_CATEGORY_BACK_TMP, XMLY_CATEGORY_BACK);
        }
    }

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
        if(CollectionUtils.isNotEmpty(xmlyCategoryList)) {
            basedMapper.batchSaveCategorys(xmlyCategoryList, tableName);
        }
    }

    /**
     * 专辑全量更新
     * 获取专辑存入中间表，完成之后原表做备份
     *
     * @throws BusinessException
     */
    public void saveAllXmlyAlbum() throws BusinessException {
        basedMapper.dropTable(XMLY_ALBUM_BACK_TMP);
        basedMapper.createTable(XMLY_ALBUM_BACK_TMP, XMLY_ALBUM_BACK);
        try {
            saveAllAlbum(XMLY_ALBUM_BACK_TMP);
        } finally {
            //源表名重命名为备份表名
            basedMapper.dropTable(XMLY_ALBUM_BACK_BAK);
            basedMapper.alterTableName(XMLY_ALBUM_BACK, XMLY_ALBUM_BACK_BAK);
            //中间表名重命名为源表名
            basedMapper.dropTable(XMLY_ALBUM_BACK);
            basedMapper.alterTableName(XMLY_ALBUM_BACK_TMP, XMLY_ALBUM_BACK);
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
        List<XmlyCategory> xmlyCategoryList = findXmlyCategory(StatusEnum.DEFAULT);
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
                throw new BusinessException("第三方请求结果为空");
            }
            JSONObject jsonObject = JSON.parseObject(responseStr);
            if (!jsonObject.containsKey("list")) {
                throw new BusinessException("第三方请求结果格式不正确，缺少list字段");
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
            if(CollectionUtils.isNotEmpty(xmlyAlbumList)){
                basedMapper.batchSaveAlbums(xmlyAlbumList, tableName);
            }
            int totalPages = jsonObject.getIntValue("totalPages");
            currentPage++;
            if (totalPages <= currentPage) {
                break;
            }
        }
        xmlyCategory.setStatus(StatusEnum.SUCCESS.getCode());
        iXmlyCategoryMapper.updateStatus(xmlyCategory);
    }

    /**
     * 节目片段全量更新
     * 获取分类存入中间表，完成之后原表做备份
     *
     * @throws BusinessException
     */
    public void saveAllXmlyTrack() throws BusinessException {
        basedMapper.dropTable(XMLY_TRACK_BACK_TMP);
        basedMapper.createTable(XMLY_TRACK_BACK_TMP, XMLY_TRACK_BACK);
        try {
            saveAllTrack(XMLY_TRACK_BACK_TMP);
        } finally {
            //源表名重命名为备份表名
            basedMapper.dropTable(XMLY_TRACK_BACK_BAK);
            basedMapper.alterTableName(XMLY_TRACK_BACK, XMLY_TRACK_BACK_BAK);
            //中间表名重命名为源表名
            basedMapper.dropTable(XMLY_TRACK_BACK);
            basedMapper.alterTableName(XMLY_TRACK_BACK_TMP, XMLY_TRACK_BACK);
        }
    }

    /**
     * 节目片段全量更新
     * 数据存入中间表
     *
     * @param tableName 中间表名
     * @throws BusinessException
     */
    private void saveAllTrack(String tableName) throws BusinessException {
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
                saveAllTrackByAlbum(xmlyAlbum, tableName);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveAllTrackByAlbum(XmlyAlbum xmlyAlbum, String tableName) throws BusinessException {
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
                if(CollectionUtils.isNotEmpty(xmlyTrackList)) {
                    basedMapper.batchSaveTracks(xmlyTrackList, tableName);
                }
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
                if(CollectionUtils.isNotEmpty(xmlyTrackList)) {
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

    /**
     * 增量更新热门专辑
     * 更新策略：拉取热门专辑，库中已存在的热门专辑的保留，不存在的热门专辑入库（status=0）
     *     更新完热门专辑后，根据status=0的专辑id去获取对应全量节目片段
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
            if(CollectionUtils.isNotEmpty(newXmlyAlbumList)) {
                iXmlyAlbumMapper.batchSave(newXmlyAlbumList);
            }
            int totalPages = jsonObject.getIntValue("totalPages");
            currentPage++;
            if (totalPages <= currentPage) {
                break;
            }
        }
    }
}
