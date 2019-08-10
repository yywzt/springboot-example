package com.example.yyw.json.vo;

import lombok.Data;

@Data
public class MeiTuanVo {

    //店铺id
    private String sid;
    //店铺名称
    private String name;
    //店铺 所在城市id
    private String cityid;
    //店铺 所在城市名称
    private String cityname;
    //店铺地址
    private String address;
    //店铺所属一级分类
    private String cate;
    //店铺所属二级分类
    private String subcate;
    //店铺平均评分
    private String avgscore;
    //店铺消费 人均价格
    private String avgprice;
    //店铺所属商圈
    private String area;
    //店铺纬度
    private String lat;
    //店铺经度
    private String lng;
    //店铺展示的首图
    private String frontimg;
    /**
     * 店铺是否有wifi
     *
     */
    private String wifi;
    /**
     * 店铺是否支持外卖
     */
    private String waimai;
    //店铺电话
    private String phone;
    //推荐菜名称           使用申请使用
    private String featuremenus ;
    //总购买人数
    private String historycoupon ;
    //唤起商家详情页面
    private String deeplinkurl ;
    //商家详情页面 h5url
    private String iurl ;
    //排队服务
    private String queuedeeplink ;
    //买单服务
    private String cashierdeeplink ;
    //可预订时预订
    private String reservedeeplink;
    //h5评论页
    private String reviewh5url ;
    //评论页
    private String reviewurl  ;
    //营业时间
    private String openinfo  ;
    //图片集
    private String[] imgurls   ;

    private double distance;

    private int isCollect; //是否收藏 0：未收藏 1已收藏
    private int id;

}
