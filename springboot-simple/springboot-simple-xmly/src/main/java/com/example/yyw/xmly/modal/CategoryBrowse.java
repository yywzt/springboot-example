package com.example.yyw.xmly.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yanzhitao
 * @date 2018/11/28
 **/
@Data
public class CategoryBrowse{
    @JsonProperty("category_id")
    private Long categoryId;
    @JsonProperty("total_page")
    private Long totalPage;
    @JsonProperty("total_count")
    private Long totalCount;
    @JsonProperty("current_page")
    private Long currentPage;
    @JsonProperty("tag_name")
    private String tagName;
    @JsonProperty("albums")
    private List<Album> albums;

}
