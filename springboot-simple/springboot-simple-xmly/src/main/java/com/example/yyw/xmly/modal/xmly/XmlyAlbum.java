package com.example.yyw.xmly.modal.xmly;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author buxianglong
 * @date 2018/12/04
 **/
@Data
public class XmlyAlbum{
    @JSONField(name = "ignore")
    private Long id;
    private Date createDate;
    private Date modifyDate;
    private Integer status;
    private Long orderNo = 0L;

    private Long extendCategoryOriginId;

    @JSONField(name = "categoryId")
    private Long categoryOriginId;
    @JSONField(name = "id")
    private Long originId;
    @JSONField(name = "kind")
    private String kind;
    @JSONField(name = "albumTitle")
    private String title;
    @JSONField(name = "tags")
    private String tags;
    @JSONField(name = "intro")
    private String introduce;
    @JSONField(name = "coverUrlSmall")
    private String coverUrlSmall;
    @JSONField(name = "coverUrlMiddle")
    private String coverUrlMiddle;
    @JSONField(name = "coverUrlLarge")
    private String coverUrlLarge;
    @JSONField(name = "playCount")
    private Long playCount;
    @JSONField(name = "favoriteCount")
    private Long favoriteCount;
    @JSONField(name = "shareCount")
    private Long shareCount;
    @JSONField(name = "includeTrackCount")
    private Integer includeTrackCount;
    @JSONField(name = "isFinished")
    private Integer isFinished;
    @JSONField(name = "canDownload")
    private Boolean canDownload;

    @JSONField(name = "updatedAt")
    private Date updateAt;
    @JSONField(name = "createdAt")
    private Date createdAt;

}