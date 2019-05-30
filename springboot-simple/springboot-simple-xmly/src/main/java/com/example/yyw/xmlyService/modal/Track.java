package com.example.yyw.xmlyService.modal;


import com.fasterxml.jackson.annotation.JsonSetter;
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
    @JsonSetter("category_id")
    private Long categoryId;
    @JsonSetter("track_title")
    private String trackTitle;
    @JsonSetter("order_num")
    private Long orderNum;
    @JsonSetter("track_tags")
    private String tags;
    @JsonSetter("track_intro")
    private String intro;
    @JsonSetter("cover_url_small")
    private String coverUrlSmall;
    @JsonSetter("cover_url_middle")
    private String coverUrlMiddle;
    @JsonSetter("cover_url_large")
    private String coverUrlLarge;
    private Long duration;
    @JsonSetter("play_count")
    private Long playCount;
    @JsonSetter("favorite_count")
    private Long favoriteCount;
    @JsonSetter("comment_count")
    private Long commentCount;
    @JsonSetter("download_count")
    private Long downloadCount;
    @JsonSetter("play_size_24_m4a")
    private Long playSize24M4a;
    @JsonSetter("play_size_64_m4a")
    private Long playSize64M4a;
    @JsonSetter("play_size_amr")
    private Long playSizeAmr;
    @JsonSetter("can_download")
    private Boolean canDownload;
    @JsonSetter("download_size")
    private Long downloadSize;
    @JsonSetter("subordinated_album")
    private SubordinatedAlbum album;
    private Integer source;
    @JsonSetter("updated_at")
    private Long updatedAt;
    @JsonSetter("created_at")
    private Long createdAt;

}
