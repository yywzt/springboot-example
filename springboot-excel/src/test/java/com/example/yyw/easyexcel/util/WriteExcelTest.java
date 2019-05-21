package com.example.yyw.easyexcel.util;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.yyw.modal.ExcelPropertyIndexModel;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/21 15:15
 * @describe
 */
@Slf4j
public class WriteExcelTest {

    @Test
    public void test1() throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("writeexcel01.xlsx");
        try {
            ExcelWriter excelWriter = new ExcelWriter(fileOutputStream, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1, 0, ExcelPropertyIndexModel.class);
            excelWriter.write(getListModal(),sheet);
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

    private List<ExcelPropertyIndexModel> getListModal(){
        List<ExcelPropertyIndexModel> excelPropertyIndexModelList = Lists.newArrayList();
        for (int i=0;i<5;i++) {
            ExcelPropertyIndexModel excelPropertyIndexModel = new ExcelPropertyIndexModel();
            excelPropertyIndexModel.setName("a" + i);
            excelPropertyIndexModel.setAge("12" + i );
            excelPropertyIndexModel.setEmail("a123@qq.com");
            excelPropertyIndexModel.setAddress("深圳");
            excelPropertyIndexModel.setSex("男");
            excelPropertyIndexModel.setHeigh("178");
            excelPropertyIndexModel.setLast("野猪佩奇" + i);

            excelPropertyIndexModelList.add(excelPropertyIndexModel);
        }
        return excelPropertyIndexModelList;
    }
}
