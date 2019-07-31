package com.example.springbootelasticsearch.controller;

import com.example.springbootelasticsearch.service.ElasticsearchTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/31 19:47
 * @describe
 */
@RestController
public class ElasticsearchTemplateController {

    @Autowired
    private ElasticsearchTemplateService elasticsearchTemplateService;

    @RequestMapping("/addIndex")
    public Object addIndex(){
        return elasticsearchTemplateService.addIndex();
    }
}
