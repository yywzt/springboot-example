package com.example.yyw.xmlyService.modal;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

/**
 * @author yanzhitao
 **/
@Data
public class TrackAll {
    @JsonSetter("total_page")
    private Long totalPage;
    @JsonSetter("total_count")
    private Long totalCount;
    @JsonSetter("current_page")
    private Long currentPage;
    @JsonSetter("category_id")
    private Long categoryId;
    @JsonSetter("tracks")
    private List<Track> tracks;

}
