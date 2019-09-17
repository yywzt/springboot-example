package com.example.yyw.easyexcel.util;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/9/16 23:06
 * @describe
 */
@Slf4j
public class WriteExcelUtil {

    public static void write(String fileName, List<? extends BaseRowModel> data, Class<? extends BaseRowModel> clazz) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        try {
            ExcelWriter excelWriter = new ExcelWriter(fileOutputStream, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1, 0, clazz);
            excelWriter.write(data,sheet);
            excelWriter.finish();
        } catch (Exception e) {
            log.error("write excel error {}",e.getMessage());
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                log.error("{}",e.getMessage());
            }
        }
    }
}
