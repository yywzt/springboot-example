package com.example.yyw.xmlyService.modal;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author yanzhitao
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
