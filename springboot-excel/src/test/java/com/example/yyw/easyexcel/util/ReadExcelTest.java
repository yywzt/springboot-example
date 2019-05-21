package com.example.yyw.easyexcel.util;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.example.yyw.modal.CarParkActiviti;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/21 14:31
 * @describe
 */
public class ReadExcelTest {

    @Test
    public void testExcel2003NoModel() {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("AA1090-2019-05-21-14_45_46.xls");
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, null, listener);
            excelReader.read();
        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testExcel2003WithReflectModel() {
        InputStream inputStream = FileUtil.getResourcesFileInputStream("AA1090-2019-05-21-14_45_46.xls");
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, null, listener);
            excelReader.read(new Sheet(1,1,CarParkActiviti.class));
        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test3(){
        List<Object> read = ReadExcelUtil.read("AA1090-2019-05-21-14_45_46.xls", CarParkActiviti.class);
        System.out.println(read);
        System.out.println(read.getClass());
        System.out.println(read.get(0));
        System.out.println(read.get(0).getClass());
    }

}
