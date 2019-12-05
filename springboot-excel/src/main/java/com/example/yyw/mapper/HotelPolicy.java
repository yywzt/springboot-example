package com.example.yyw.mapper;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.example.yyw.util.DateUtil;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/11/6 22:09
 * @description
 */
@Data
public class HotelPolicy extends BaseRowModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;          // ID
    public Date createDate = DateUtil.init(2019,11,11,10,59,00,000);  // 创建日期
    public Date modifyDate;  // 修改日期

    private String channelId = "AA1090";

    @ExcelProperty(value = "HotelId" ,index = 0)
    private String hotelId;
    @ExcelProperty(value = "Type" ,index = 1)
    private String type;
    @ExcelProperty(value = "Info" ,index = 2)
    private String info;
}
