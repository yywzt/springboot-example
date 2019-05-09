package com.example.yyw.mongodb.controller;


import com.example.yyw.mongodb.model.Article;
import com.example.yyw.mongodb.model.Student;
import com.example.yyw.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * MongoDB数据库的基本操作
 * */
@RestController
@RequestMapping("/testMongoDb")
public class testMongoDbController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**查询操作*/
    @RequestMapping("/page/{pageNumber}/{pageSize}")
    public List<Article> page(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize){
        Query query = Query.query(Criteria.where("author").is("yinjihuan")).skip(pageNumber).limit(pageSize);
        List<Article> articles = mongoTemplate.find(query, Article.class);
        return articles;
    }

    @RequestMapping("/mongoTemplateSave")
    public Object mongoTemplateSave(){
        Student student = new Student();
        student.setName("甲");
        student.setAge("12");
        student.setSex("男");
        Student save = mongoTemplate.save(student);
        return ResultUtil.successResultV2(save);
    }

    @RequestMapping("/mongoTemplateSaveToCollection")
    public Object mongoTemplateSaveToCollection(){
        Student student = new Student();
        student.setName("甲");
        student.setAge("12");
        student.setSex("男");
        Student save = mongoTemplate.save(student,"student");
        return ResultUtil.successResultV2(save);
    }
}
