package com.example.yyw.json;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/30 17:27
 * @describe
 */
@RestController
public class TestController {

    @RequestMapping("/test1")
    public Object test1(){
        json json = new json();
        json.setName("aaa");
        json.setGender("男");
        json.setAge(12);
        return json;
    }
}
