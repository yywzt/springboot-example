package com.example.yyw.json.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: huojie
 * @Date: 2018/11/6 0006 14:56
 * @Description:酒店详情 同otherIf com.bying.otherif.model.xml.hotel.HotelsVo
 */
@Getter
@Setter
public class Hotel {

    private String HotelId;//CN 酒店 ID
    private String HotelName;//CN 酒店名称
    private String CountryId;
    private String CountryName;
    private String ProvinceId;
    private String ProvinceName;
    private String CityId;
    private String CityName;
    private String Address;//地址
    private String PostCode;//邮编
    private String Email;//电子邮箱
    private String StartBusinessDate;//开业时间
    private String Repairdate;//最后装修时间
    private String RecommendedLevel;//推荐级别
    private String Star;//星级
    private String StarName;//星级名称
    private String Lon;//经度
    private String Lat;//纬度
    private String Intro;//酒店简介
    private String AllowWebSale;//是否允许在互联网销售,0: 不允许 1:允许
    private String Guide;//客户指引

    private int isCollect; //是否收藏 0：未收藏 1已收藏


}
