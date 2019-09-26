package com.example.yyw.easyexcel.util;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/9/16 23:06
 * @describe
 */
@Slf4j
public class WriteExcelUtil {

    public static void write(String fileName, List<? extends BaseRowModel> data, Class<? extends BaseRowModel> clazz) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
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

    public static void write(List<? extends BaseRowModel> data, Class<? extends BaseRowModel> clazz, ZipOutputStream zos) throws IOException {
        String fileName = UUID.randomUUID().toString() + ".xlsx";
        ZipEntry zipEntry = new ZipEntry(fileName);
        // 放到压缩包里
        zos.putNextEntry(zipEntry);
        try {
            ExcelWriter excelWriter = new ExcelWriter(zos, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1, 0, clazz);
            excelWriter.write(data,sheet);
            excelWriter.finish();
        } catch (Exception e) {
            log.error("write excel error {}", e.getMessage());
        }
    }
}
