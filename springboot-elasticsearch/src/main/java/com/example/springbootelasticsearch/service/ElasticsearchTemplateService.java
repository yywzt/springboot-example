package com.example.springbootelasticsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/31 19:47
 * @describe
 */
@Service
public class ElasticsearchTemplateService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public Object addIndex() {
//        elasticsearchTemplate.refresh();
        return null;
    }
}
