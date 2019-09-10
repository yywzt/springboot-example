package com.example.yyw.json.vo;

import java.util.List;

/**
 * @Auther: huojie
 * @Date: 2018/11/6 0006 14:48
 * @Description:酒店集合vo
 */
public class HotelsVo {


    private int HotelCount;

    private List<Hotel> hotelList;

    private PageInfo pageInfo;

    public int getHotelCount() {
        return HotelCount;
    }

    public void setHotelCount(int hotelCount) {
        HotelCount = hotelCount;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
