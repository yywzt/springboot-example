package com.example.yyw.xmly.repository;

import com.example.yyw.xmly.modal.mongo.xmly.XmlyCategoryMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/8 17:30
 * @describe
 */
public interface XmlyCategoryRepository extends MongoRepository<XmlyCategoryMongo,String> {
}
