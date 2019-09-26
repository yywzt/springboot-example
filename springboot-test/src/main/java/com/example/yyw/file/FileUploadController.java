package com.example.yyw.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/9/26 22:31
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/upload")
    public void method_upload(MultipartFile file) throws IOException {
        String url = "http://111.230.137.157:18082/rest/file/uploadFile";
        // 获取文件名
        String fileName = file.getOriginalFilename();

        ByteArrayResource byteArrayResource = new ByteArrayResource(file.getBytes()){
            @Override
            public String getFilename() {
                return fileName;
            }
        };

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", byteArrayResource);
        map.add("channelId", "AA1090");
        map.add("fileType", "2");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(responseEntity);
    }
}
