package com.example.yyw.xmlyService.modal;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 节目
 *
 * @author yanzhitao
 **/
@Data
public class Track{
    private Long id;
    private String kind;
    @JsonProperty("category_id")
    private Long categoryId;
    @JsonProperty("track_title")
    private String trackTitle;
    @JsonProperty("order_num")
    private Long orderNum;
    @JsonProperty("track_tags")
    private String tags;
    @JsonProperty("track_intro")
    private String intro;
    @JsonProperty("cover_url_small")
    private String coverUrlSmall;
    @JsonProperty("cover_url_middle")
    private String coverUrlMiddle;
    @JsonProperty("cover_url_large")
    private String coverUrlLarge;
    private Long duration;
    @JsonProperty("play_count")
    private Long playCount;
    @JsonProperty("favorite_count")
    private Long favoriteCount;
    @JsonProperty("comment_count")
    private Long commentCount;
    @JsonProperty("download_count")
    private Long downloadCount;
    @JsonProperty("play_size_24_m4a")
    private Long playSize24M4a;
    @JsonProperty("play_size_64_m4a")
    private Long playSize64M4a;
    @JsonProperty("play_size_amr")
    private Long playSizeAmr;
    @JsonProperty("can_download")
    private Boolean canDownload;
    @JsonProperty("download_size")
    private Long downloadSize;
    @JsonProperty("subordinated_album")
    private SubordinatedAlbum album;
    private Integer source;
    @JsonProperty("updated_at")
    private Long updatedAt;
    @JsonProperty("created_at")
    private Long createdAt;

}
