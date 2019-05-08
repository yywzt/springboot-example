package com.example.yyw.xmly.modal.mongo.xmly;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author yanzhitao
 * @date 2019/05/07
 **/
@Data
public class XmlyCategoryMongo{
    @JSONField(name = "ignore")
    private String id;
    private Date createDate;
    private Date modifyDate;
    private Integer status;

    @JSONField(name = "kind")
    private String kind;
    @JSONField(name = "id")
    private Long originId;
    @JSONField(name = "category_name")
    private String name;
    @JSONField(name = "cover_url_small")
    private String coverUrlSmall;
    @JSONField(name = "cover_url_middle")
    private String coverUrlMiddle;
    @JSONField(name = "cover_url_large")
    private String coverUrlLarge;
    @JSONField(name = "order_num")
    private Integer orderNo;

}
