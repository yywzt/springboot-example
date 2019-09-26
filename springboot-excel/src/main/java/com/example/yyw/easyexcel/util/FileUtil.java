package com.example.yyw.easyexcel.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/21 14:54
 */
public class FileUtil {

    private static final String DEFAULT_UPLOAD_PATH = File.separator + "data" + File.separator + "file" + File.separator;

    static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    static InputStream getInputStream(String fileName) throws IOException {
        File file = new File(fileName);
        return FileUtils.openInputStream(file);
    }

    public static String uploadFile(MultipartFile multipartFile) throws IOException {
        return uploadFile(multipartFile, DEFAULT_UPLOAD_PATH);
    }

    public static String uploadFile(MultipartFile multipartFile, String path) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        File file = new File(path + newFileName);
        multipartFile.transferTo(file);
        return file.getPath();
    }
}
