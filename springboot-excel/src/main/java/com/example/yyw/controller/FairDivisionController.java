package com.example.yyw.controller;

import com.example.yyw.easyexcel.util.FileUtil;
import com.example.yyw.service.FairDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/9/21 13:26
 */
@RestController
public class FairDivisionController {

    private final FairDivisionService fairDivisionService;

    @Autowired
    public FairDivisionController(FairDivisionService fairDivisionService) {
        this.fairDivisionService = fairDivisionService;
    }

    @RequestMapping("/groupExcel")
    public void groupExcel(HttpServletResponse response, @RequestParam("file") MultipartFile multipartFile, @RequestParam double max) throws IOException {
        fairDivisionService.groupExcel(response, multipartFile, max);
    }

    @RequestMapping("/uploadFile")
    public String method(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return FileUtil.uploadFile(multipartFile);
    }

}
