package com.example.yyw.mongodb.repository;


import com.example.yyw.mongodb.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * @author yanzt
 * @date 2018/6/27 14:30
 * @description MongoRepository是jpa框架，参考data Jpa方法命名规范
 */
public interface ArticleRepository extends MongoRepository<Article,String> {

    List<Article> findByAuthor(String author);
}
