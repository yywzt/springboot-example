package com.example.yyw.easyexcel.util;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.example.yyw.modal.CarParkActiviti;
import com.example.yyw.modal.ExcelPropertyIndexModel2;
import com.example.yyw.util.BigDecimalUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.yyw.easyexcel.util.ReadExcelUtil.read;

/**
 * @author ywyw2424@foxmail.com
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
        List<Object> read = read("AA1090-2019-05-21-14_45_46.xls", CarParkActiviti.class);
        System.out.println(read);
        System.out.println(read.getClass());
        System.out.println(read.get(0));
        System.out.println(read.get(0).getClass());
    }

    @Test
    public void test_4(){
        List<Object> read = read("20190916.xlsx", ExcelPropertyIndexModel2.class);
        System.out.println(read);
        System.out.println(read.getClass());
    }

    @Test
    public void test_5(){
        List<Double> doubleList = new ArrayList<Double>(){{
            add(15.1D);
            add(12.2D);
            add(12.3D);
            add(12.4D);
            add(12.5D);
            add(11.9D);
            add(11.8D);
            add(12.0D);
        }};
        System.out.println(doubleList);
        List<Double> collect = doubleList.stream().sorted((o1, o2) -> {
            BigDecimal subtract = BigDecimalUtil.subtract(o1, o2);
            return subtract.doubleValue() > 0D ? 1 :
                    subtract.doubleValue() < 0D ? -1 : 0;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test_6(){
        BigDecimal subtract = BigDecimalUtil.subtract(12.3D, 12.4D);
        System.out.println(subtract.doubleValue());
        System.out.println(Math.ceil(subtract.doubleValue()));
        System.out.println((int) Math.ceil(subtract.doubleValue()));
    }

}
