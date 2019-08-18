package com.example.yyw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/18 14:28
 * @describe
 */
@RestController
@RequestMapping("/upload")
public class UploadFileController {

    private static final String UPLOAD_PATH = "D:\\yw\\upload\\";

    @RequestMapping("/file")
    public String uploadFile(@RequestParam MultipartFile file, @RequestParam(required = false,defaultValue = "yyw") String path) throws IOException {
        String originalFilename = file.getOriginalFilename();
        File newFile = new File(UPLOAD_PATH + path + File.separator + UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf(".")));
        if(!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdirs();
        }
        file.transferTo(newFile);
        return newFile.getPath();
    }
}
