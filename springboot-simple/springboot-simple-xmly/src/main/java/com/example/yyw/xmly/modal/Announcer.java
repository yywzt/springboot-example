package com.example.yyw.xmly.modal;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author buxianglong
 * @date 2018/11/23
 **/
@Data
public class Announcer{
    private Long id;
    @JsonProperty("nickname")
    private String nickName;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("is_verified")
    private Boolean isVerified;

}
