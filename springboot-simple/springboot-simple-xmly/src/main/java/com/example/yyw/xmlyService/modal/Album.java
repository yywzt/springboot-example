package com.example.yyw.xmlyService.modal;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * 专辑
 *
 * @author yanzhitao
 **/
@Data
public class Album{
    private Long id;
    private String kind;
    @JsonSetter("album_title")
    private String albumTitle;
    @JsonSetter("category_id")
    private int categoryId;
    @JsonSetter("album_tags")
    private String tags;
    @JsonSetter("album_intro")
    private String intro;
    @JsonSetter("cover_url_small")
    private String coverUrlSmall;
    @JsonSetter("cover_url_middle")
    private String coverUrlMiddle;
    @JsonSetter("cover_url_large")
    private String coverUrlLarge;
    @JsonSetter("play_count")
    private Long playCount;
    @JsonSetter("favorite_count")
    private Long favoriteCount;
    @JsonSetter("subscribe_count")
    private Long subscribeCount;
    @JsonSetter("share_count")
    private Long shareCount;
    @JsonSetter("include_track_count")
    private Integer includeTrackCount;
    @JsonSetter("is_finished")
    private Integer isFinished;
    @JsonSetter("can_download")
    private Boolean canDownload;
    @JsonSetter("updated_at")
    private Long updatedAt;
    @JsonSetter("created_at")
    private Long createdAt;

}
