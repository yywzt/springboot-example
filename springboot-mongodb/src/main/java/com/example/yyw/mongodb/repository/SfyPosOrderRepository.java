package com.example.yyw.mongodb.repository;


import com.example.yyw.mongodb.model.SfyPosOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yanzt
 * @date 2018/6/27 14:30
 * @description MongoRepository是jpa框架，参考data Jpa方法命名规范
 */
public interface SfyPosOrderRepository extends MongoRepository<SfyPosOrder,String> {

}
