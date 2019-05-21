package com.example.yyw.easyexcel.util;

import java.io.InputStream;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/21 14:54
 * @describe
 */
public class FileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }
}
