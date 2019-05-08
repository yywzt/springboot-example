package com.example.yyw.xmlyService.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author yanzhitao
 * @date 2018/11/28
 **/
@Data
public class Tag{
    @JsonProperty("tag_name")
    private String tagName;
    private String kind;

}
