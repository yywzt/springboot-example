package com.example.yyw.xmly.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.yyw.util.HttpUtil;
import com.example.yyw.util.ResultUtil;
import com.example.yyw.xmly.enums.StatusEnum;
import com.example.yyw.xmly.exception.BusinessException;
import com.example.yyw.xmly.modal.mongo.xmly.XmlyAlbumMongo;
import com.example.yyw.xmly.modal.mongo.xmly.XmlyCategoryMongo;
import com.example.yyw.xmly.modal.mongo.xmly.XmlyTrackMongo;
import com.example.yyw.xmly.repository.XmlyAlbumMongoRepository;
import com.example.yyw.xmly.repository.XmlyCategoryRepository;
import com.example.yyw.xmly.repository.XmlyTrackMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
public class IXmlyMongoService {

    private static final String OTHER_IF_URL = "http://127.0.0.1:19091";

    @Autowired
    private XmlyCategoryRepository xmlyCategoryRepository;
    @Autowired
    private XmlyAlbumMongoRepository xmlyAlbumMongoRepository;
    @Autowired
    private XmlyTrackMongoRepository xmlyTrackMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存分类
     *
     * @throws BusinessException
     */
    public Object saveCategory() throws BusinessException {
        String url = OTHER_IF_URL + "/ximalaya/category/list";
        String responseStr = HttpUtil.httpGet(url);
        if(StringUtils.isBlank(responseStr)){
            throw new BusinessException("第三方请求结果为空");
        }
        JSONObject jsonObject = JSON.parseObject(responseStr);
        if(!jsonObject.containsKey("data")){
            throw new BusinessException("第三方请求结果格式不正确，缺少data字段");
        }
        List<XmlyCategoryMongo> xmlyCategoryMongoList = jsonObject.getJSONArray("data").toJavaList(XmlyCategoryMongo.class);
        if(CollectionUtils.isEmpty(xmlyCategoryMongoList)){
            throw new BusinessException("分类为空");
        }
        Date currentDate = new Date();
        xmlyCategoryMongoList.forEach(xmlyCategoryMongo -> {
            xmlyCategoryMongo.setCreateDate(currentDate);
            xmlyCategoryMongo.setModifyDate(currentDate);
            xmlyCategoryMongo.setStatus(StatusEnum.DEFAULT.getCode());
        });
        xmlyCategoryRepository.insert(xmlyCategoryMongoList);
        return ResultUtil.successResult();
    }

    /**
     * 保存专辑
     *
     * @throws BusinessException
     */

    public Object saveAlbum() throws BusinessException{
        List<XmlyCategoryMongo> xmlyCategoryMongoList = findXmlyCategoryMongo(StatusEnum.DEFAULT);
        for(XmlyCategoryMongo xmlyCategoryMongo : xmlyCategoryMongoList){
            saveAlbumByCategory(xmlyCategoryMongo);
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
                log.error("saveAlbum error : {}", e.getMessage());
            }
        }
        return ResultUtil.successResult();
    }

    private void saveAlbumByCategory(XmlyCategoryMongo xmlyCategoryMongo) throws BusinessException{
        if(null == xmlyCategoryMongo){
            return;
        }
        Long categoryId = xmlyCategoryMongo.getOriginId();
        int currentPage = 0;
        int pageSize = 200;
        while(true){
            StringBuilder url = new StringBuilder(OTHER_IF_URL + "/ximalaya/album/list?");
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
            List<XmlyAlbumMongo> xmlyAlbumMongoList = jsonObject.getJSONArray("list").toJavaList(XmlyAlbumMongo.class);
            xmlyAlbumMongoList.forEach(System.out::println);
            Date currentDate = new Date();
            xmlyAlbumMongoList.forEach(xmlyAlbum -> {
                xmlyAlbum.setExtendCategoryOriginId(categoryId);
                xmlyAlbum.setCreateDate(currentDate);
                xmlyAlbum.setModifyDate(currentDate);
                xmlyAlbum.setStatus(StatusEnum.DEFAULT.getCode());
            });
            xmlyAlbumMongoRepository.insert(xmlyAlbumMongoList);
            int totalPages = jsonObject.getIntValue("totalPages");
            currentPage++;
            if(totalPages <= currentPage){
                break;
            }
        }
        xmlyCategoryMongo.setStatus(StatusEnum.SUCCESS.getCode());
        xmlyCategoryRepository.save(xmlyCategoryMongo);
    }

    /**
     * 保存节目碎片
     *
     * @throws BusinessException
     */
    public Object saveTrack() throws BusinessException{
        List<XmlyCategoryMongo> xmlyCategoryMongoList = findXmlyCategoryMongo(null);
        for(XmlyCategoryMongo xmlyCategoryMongo : xmlyCategoryMongoList){
            if(null == xmlyCategoryMongo){
                continue;
            }
            List<XmlyAlbumMongo> xmlyAlbumMongoList = findXmlyAlbumByCategoryMongo(xmlyCategoryMongo);
            if(CollectionUtils.isEmpty(xmlyAlbumMongoList)){
                continue;
            }
            for(XmlyAlbumMongo xmlyAlbumMongo : xmlyAlbumMongoList){
                if(xmlyAlbumMongo.getStatus().intValue() == StatusEnum.DEFAULT.getCode()){
                    saveTrackByAlbum(xmlyAlbumMongo);
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
     * @param xmlyAlbumMongo
     */
    private void saveTrackByAlbum(XmlyAlbumMongo xmlyAlbumMongo) throws BusinessException{
        if(null == xmlyAlbumMongo){
            return;
        }
        try{
            Long albumId = xmlyAlbumMongo.getOriginId();
            int currentPage = 0;
            int pageSize = 200;
            while(true){
                StringBuilder url = new StringBuilder(OTHER_IF_URL + "/ximalaya/track/byAlbum?");
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
                List<XmlyTrackMongo> xmlyTrackMongoList = jsonObject.getJSONArray("list").toJavaList(XmlyTrackMongo.class);
                if(CollectionUtils.isEmpty(xmlyTrackMongoList)){
                    break;
                }
                Date currentDate = new Date();
                xmlyTrackMongoList.forEach(xmlyTrackMongo -> {
                    xmlyTrackMongo.setExtendCategoryOriginId(xmlyAlbumMongo.getExtendCategoryOriginId());
                    xmlyTrackMongo.setAlbumOriginId(albumId);
                    xmlyTrackMongo.setCreateDate(currentDate);
                    xmlyTrackMongo.setModifyDate(currentDate);
                    xmlyTrackMongo.setStatus(StatusEnum.DEFAULT.getCode());
                });
                xmlyTrackMongoRepository.insert(xmlyTrackMongoList);
                int totalPages = jsonObject.getIntValue("totalPages");
                currentPage++;
                if(totalPages <= currentPage){
                    break;
                }
            }
            xmlyAlbumMongo.setStatus(StatusEnum.SUCCESS.getCode());
            xmlyAlbumMongoRepository.save(xmlyAlbumMongo);
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
    private List<XmlyCategoryMongo> findXmlyCategoryMongo(StatusEnum statusEnum) throws BusinessException{
        Map<String, Object> params = new HashMap<>();
        Query query = null;
        if(null != statusEnum){
            query = Query.query(Criteria.where("status").is(statusEnum.getCode()));
        }else {
            query = new Query();
        }
        List<XmlyCategoryMongo> xmlyCategoryMongoList = mongoTemplate.find(query, XmlyCategoryMongo.class);
        return xmlyCategoryMongoList;
    }

    /**
     * 根据分类，获取专辑集合
     *
     * @param xmlyCategoryMongo 分类对象
     * @return 专辑集合
     */
    private List<XmlyAlbumMongo> findXmlyAlbumByCategoryMongo(XmlyCategoryMongo xmlyCategoryMongo){
        if(null == xmlyCategoryMongo){
            return new ArrayList<>();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("extendCategoryOriginId", xmlyCategoryMongo.getOriginId());
        params.put("status", StatusEnum.DEFAULT.getCode());
        Query query = Query.query(Criteria.where("extendCategoryOriginId").is(xmlyCategoryMongo.getOriginId()).and("status").is(StatusEnum.DEFAULT.getCode()));
        List<XmlyAlbumMongo> xmlyAlbumMongoList = mongoTemplate.find(query, XmlyAlbumMongo.class);
        return xmlyAlbumMongoList;
    }
}
