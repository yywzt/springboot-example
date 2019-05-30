package com.example.yyw.xmlyService.modal;


import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * @author yanzhitao
 **/
@Data
public class SubordinatedAlbum{
    private Long id;
    @JsonSetter("album_title")
    private String albumTitle;
    @JsonSetter("cover_url_small")
    private String coverUrlSmall;
    @JsonSetter("cover_url_middle")
    private String coverUrlMiddle;
    @JsonSetter("cover_url_large")
    private String coverUrlLarge;

}
