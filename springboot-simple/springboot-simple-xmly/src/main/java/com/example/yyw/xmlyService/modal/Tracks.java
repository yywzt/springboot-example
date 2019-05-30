package com.example.yyw.xmlyService.modal;


import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;


/**
 * @author yanzhitao
 * @date 2018/11/28
 **/
@Data
public class Tracks{
    @JsonSetter("tracks")
    private List<Track> tracks;

}
