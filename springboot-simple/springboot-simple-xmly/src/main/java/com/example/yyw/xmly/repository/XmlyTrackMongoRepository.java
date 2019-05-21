package com.example.yyw.xmly.repository;

import com.example.yyw.xmly.modal.mongo.xmly.XmlyTrackMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/8 17:43
 * @describe
 */
public interface XmlyTrackMongoRepository extends MongoRepository<XmlyTrackMongo,String> {
}
