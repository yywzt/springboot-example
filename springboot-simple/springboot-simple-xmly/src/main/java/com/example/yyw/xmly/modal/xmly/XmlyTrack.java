package com.example.yyw.xmly.modal.xmly;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author yanzhitao
 * @date 2019/05/07
 **/
@Data
public class XmlyTrack{
    @JSONField(name = "ignore")
    private Long id;
    private Date createDate;
    private Date modifyDate;
    private Integer status;
    @JSONField(name = "orderNum")
    private Long orderNo;

    private Long extendCategoryOriginId;
    @JSONField(name = "categoryId")
    private Long categoryOriginId;
    private Long AlbumOriginId;

    @JSONField(name = "id")
    private Long originId;
    @JSONField(name = "kind")
    private String kind;
    @JSONField(name = "trackTitle")
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

    @JSONField(name = "duration")
    private Long duration;
    @JSONField(name = "playCount")
    private Long playCount;
    @JSONField(name = "favoriteCount")
    private Long favoriteCount;
    @JSONField(name = "commentCount")
    private Long commentCount;
    @JSONField(name = "downloadCount")
    private Long downloadCount;

    @JSONField(name = "playSize24M4a")
    private Long playSize24M4a;
    @JSONField(name = "playSize64M4a")
    private Long playSize64M4a;
    @JSONField(name = "playSizeAmr")
    private Long playSizeAmr;
    @JSONField(name = "source")
    private Integer source;
    @JSONField(name = "updatedAt")
    private Date updateAt;
    @JSONField(name = "createdAt")
    private Date createdAt;

}
