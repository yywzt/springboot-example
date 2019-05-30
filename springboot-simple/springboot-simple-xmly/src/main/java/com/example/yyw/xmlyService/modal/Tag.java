package com.example.yyw.xmlyService.modal;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * @author yanzhitao
 * @date 2018/11/28
 **/
@Data
public class Tag{
    @JsonSetter("tag_name")
    private String tagName;
    private String kind;

}
