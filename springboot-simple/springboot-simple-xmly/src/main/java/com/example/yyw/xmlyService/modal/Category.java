package com.example.yyw.xmlyService.modal;


import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * @author yanzhitao
 * @date 2018/11/26
 **/
@Data
public class Category{
    private Long id;
    private String kind;
    @JsonSetter("category_name")
    private String categoryName;
    @JsonSetter("cover_url_small")
    private String coverUrlSmall;
    @JsonSetter("cover_url_middle")
    private String coverUrlMiddle;
    @JsonSetter("cover_url_large")
    private String coverUrlLarge;
    @JsonSetter("order_num")
    private Long orderNum;

}
