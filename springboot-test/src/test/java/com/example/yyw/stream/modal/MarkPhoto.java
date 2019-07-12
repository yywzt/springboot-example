package com.example.yyw.stream.modal;

import lombok.Data;

import java.util.Date;

@Data
public class MarkPhoto {

    private Long id;
    private Long userId;
    private String channelId;
    private String title;
    private java.sql.Date date;
    private String city;
    private Date createDate;
    private int enableStatus;
}
