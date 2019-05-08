package com.example.yyw.xmlyService.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yanzhitao
 **/
@Data
public class AlbumBrowse{
    @JsonProperty("total_page")
    private Long totalPage;
    @JsonProperty("total_count")
    private Long totalCount;
    @JsonProperty("current_page")
    private Long currentPage;
    @JsonProperty("tracks")
    private List<Track> tracks;

}
