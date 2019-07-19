package com.example.yyw.redis_map;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkinMallStatisticalVo implements Serializable {
    private String createDate;
    private int userCount; //截止到当日用户总数
    private int enterCount; //进入商城页面用户数
    private int payCount; //付费用户数
    private int userAddCount; //当日新增用户数
    private int themeCount; //进入天天主题用户数
    private int detailsCount;//进入详情页用户
    private String netType; //网络类型

    private int enterNumber; //进入商城页面总次数
    private int payNumber; //付费次数
    private float payAmount; //付费总金额

    private int newEnterCount; //新版进入商城页面用户数

}
