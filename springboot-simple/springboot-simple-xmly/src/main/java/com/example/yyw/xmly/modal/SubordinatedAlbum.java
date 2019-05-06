package com.example.yyw.xmly.modal;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author buxianglong
 **/
@Data
public class SubordinatedAlbum{
    private Long id;
    @JsonProperty("album_title")
    private String albumTitle;
    @JsonProperty("cover_url_small")
    private String coverUrlSmall;
    @JsonProperty("cover_url_middle")
    private String coverUrlMiddle;
    @JsonProperty("cover_url_large")
    private String coverUrlLarge;

}
