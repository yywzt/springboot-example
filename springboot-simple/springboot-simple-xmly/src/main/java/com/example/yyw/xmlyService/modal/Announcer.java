package com.example.yyw.xmlyService.modal;


import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * @author yanzhitao
 * @date 2018/11/23
 **/
@Data
public class Announcer{
    private Long id;
    @JsonSetter("nickname")
    private String nickName;
    @JsonSetter("avatar_url")
    private String avatarUrl;
    @JsonSetter("is_verified")
    private Boolean isVerified;

}
