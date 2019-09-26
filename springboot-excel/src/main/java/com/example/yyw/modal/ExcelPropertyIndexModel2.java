package com.example.yyw.modal;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/21 15:14
 * @describe
 */
@Data
public class ExcelPropertyIndexModel2 extends BaseRowModel {

    @ExcelProperty(value = "订单号码" ,index = 0)
    private String tno;

    @ExcelProperty(value = "物料编码",index = 1)
    private String sno;

    @ExcelProperty(value = "物料名称",index = 2)
    private String name;

    @ExcelProperty(value = "规格型号",index = 3)
    private String type;

    @ExcelProperty(value = "单位",index = 4)
    private String company;

    @ExcelProperty(value = "数量",index = 5)
    private String count;

    @ExcelProperty(value = "单价",index = 6)
    private String price;

    @ExcelProperty(value = "金额",index = 7)
    private Double money;
}
