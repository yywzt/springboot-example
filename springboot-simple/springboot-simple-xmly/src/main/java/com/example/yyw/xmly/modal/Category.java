package com.example.yyw.xmly.modal;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author yanzhitao
 * @date 2018/11/26
 **/
@Data
public class Category{
    private Long id;
    private String kind;
    @JsonProperty("category_name")
    private String categoryName;
    @JsonProperty("cover_url_small")
    private String coverUrlSmall;
    @JsonProperty("cover_url_middle")
    private String coverUrlMiddle;
    @JsonProperty("cover_url_large")
    private String coverUrlLarge;
    @JsonProperty("order_num")
    private Long orderNum;

}
