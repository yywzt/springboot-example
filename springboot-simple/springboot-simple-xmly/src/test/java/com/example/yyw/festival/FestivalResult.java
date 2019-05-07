package com.example.yyw.festival;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiacheng on 2017/12/20 0020.
 */
public class FestivalResult {
    /**
     * {
     * "status": 200,
     * "message": "success",
     * "data": {
     * "year": 2018,
     * "month": 11,
     * "day": 8,
     * "festivalList": [
     * "记者节"
     * ],
     * "jieqi": {
     * "8": "立冬",
     * "22": "小雪"
     * }
     * }
     * }
     * year : 2018
     * month : 11
     * day : 08
     * festivalList : ["记者节"]
     * jieqi : {"8": "立冬","22": "小雪"}
     */
    private String year;
    private String month;
    private String day;
    private List<String> festivalList = new ArrayList<>();
    private Map<String, String> jieqi = new HashMap<>();

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<String> getFestivalList() {
        return festivalList;
    }

    public void setFestivalList(List<String> festivalList) {
        this.festivalList = festivalList;
    }

    public Map<String, String> getJieqi() {
        return jieqi;
    }

    public void setJieqi(Map<String, String> jieqi) {
        this.jieqi = jieqi;
    }
}
