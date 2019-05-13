package com.example.yyw.xmly.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.yyw.util.HttpUtil;
import com.example.yyw.util.ResultUtil;
import com.example.yyw.xmly.enums.StatusEnum;
import com.example.yyw.xmly.exception.BusinessException;
import com.example.yyw.xmly.mapper.IXmlyAlbumMapper;
import com.example.yyw.xmly.mapper.IXmlyCategoryMapper;
import com.example.yyw.xmly.mapper.IXmlyTrackMapper;
import com.example.yyw.xmly.modal.xmly.XmlyAlbum;
import com.example.yyw.xmly.modal.xmly.XmlyCategory;
import com.example.yyw.xmly.modal.xmly.XmlyTrack;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

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
            List<XmlyAlbum> xmlyAlbumList = jsonObject.getJSONArray("list").toJavaList(XmlyAlbum.class);
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
                List<XmlyTrack> xmlyTrackList = jsonObject.getJSONArray("list").toJavaList(XmlyTrack.class);
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
}
