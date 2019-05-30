package com.example.yyw.xmlyService.modal;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

/**
 * @author yanzhitao
 * @date 2018/11/28
 **/
@Data
public class CategoryBrowse{
    @JsonSetter("category_id")
    private Long categoryId;
    @JsonSetter("total_page")
    private Long totalPage;
    @JsonSetter("total_count")
    private Long totalCount;
    @JsonSetter("current_page")
    private Long currentPage;
    @JsonSetter("tag_name")
    private String tagName;
    @JsonSetter("albums")
    private List<Album> albums;

}
