package com.example.yyw.main;

import com.example.yyw.xmly.exception.BusinessException;
import com.example.yyw.xmly.service.IXmlyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/3 14:54
 * @describe
 */
@Slf4j
public class XmlySyncAllWorke {

    @Autowired
    private IXmlyService iXmlyService;

    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("喜马拉雅分类、专辑、声音节目全量同步任务")
                .getOrCreate();
        IXmlyService iXmlyService = new IXmlyService();
        try {
            iXmlyService.saveAll();
        } catch (BusinessException be) {
            log.error("Exception when save all track.", be);
        }
        sparkSession.close();
    }
}
