package com.example.yyw.jpa.modal;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/10/14 17:20
 * @description
 */
@Data
@Entity
@Table(name = "qr_code_info")
public class QrCodeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(updatable = false)
    private Date createDate;

    private Date modifyDate;

    @Column(columnDefinition = "INT default 1")
    private Integer enableStatus;

    @Column(columnDefinition = "varchar(60) default null")
    private String channelId;

    @Column(columnDefinition = "varchar(60) default null")
    private String type;

    /**
     * 地址
     * */
    @Column(columnDefinition = "varchar(90) default null")
    private String qrcode;

    /**
     * 中文描述
     */
    @Column(columnDefinition = "text default null")
    private String description;

    /**
     * 英文描述
     * */
    @Column(columnDefinition = "text default null")
    private String descriptionEn;

    /**
     * 备注信息
     * */
    @Column(columnDefinition = "text default null")
    private String remark;
}