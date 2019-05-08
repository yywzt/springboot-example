package com.example.yyw.xmlyService.modal;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


/**
 * @author yanzhitao
 * @date 2018/11/28
 **/
@Data
public class Tracks{
    @JsonProperty("tracks")
    private List<Track> tracks;

}
