package com.example.yyw.easyexcel.util;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/21 14:31
 * @describe
 */
@Slf4j
public class ReadExcelUtil {

    public static List<Object> read(String fileName, Class<? extends BaseRowModel> clazz){
        InputStream inputStream = FileUtil.getResourcesFileInputStream(fileName);
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, null, listener);
            excelReader.read(new Sheet(1,1,clazz));
            return listener.getDatas();
        } catch (Exception e) {
            log.error("{}",e.getMessage());
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ExcelListener extends AnalysisEventListener {

        private List<Object> datas = new ArrayList<Object>();

        @Override
        public void invoke(Object object, AnalysisContext context) {
//            System.out.println("当前行："+context.getCurrentRowNum());
//            System.out.println(object);
            datas.add(object);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
            doSomething(object);//根据自己业务做处理
        }

        private void doSomething(Object object) {
            //1、入库调用接口
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // datas.clear();//解析结束销毁不用的资源
        }

        public List<Object> getDatas() {
            return datas;
        }
    }

}
