package com.example.yyw.modal;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/21 15:03
 * @describe
 */
@Data
public class CarParkActiviti extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String channelId;

    @ExcelProperty(index = 1)
    private String mainTitle;

    @ExcelProperty(index = 2)
    private String iconPath;
}
