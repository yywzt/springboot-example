package com.example.yyw.xmly.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 专辑
 *
 * @author buxianglong
 **/
@Data
public class Album{
    private Long id;
    private String kind;
    @JsonProperty("album_title")
    private String albumTitle;
    @JsonProperty("category_id")
    private int categoryId;
    @JsonProperty("album_tags")
    private String tags;
    @JsonProperty("album_intro")
    private String intro;
    @JsonProperty("cover_url_small")
    private String coverUrlSmall;
    @JsonProperty("cover_url_middle")
    private String coverUrlMiddle;
    @JsonProperty("cover_url_large")
    private String coverUrlLarge;
    @JsonProperty("play_count")
    private Long playCount;
    @JsonProperty("favorite_count")
    private Long favoriteCount;
    @JsonProperty("share_count")
    private Long shareCount;
    @JsonProperty("include_track_count")
    private Integer includeTrackCount;
    @JsonProperty("is_finished")
    private Integer isFinished;
    @JsonProperty("can_download")
    private Boolean canDownload;
    @JsonProperty("updated_at")
    private Long updatedAt;
    @JsonProperty("created_at")
    private Long createdAt;

}
