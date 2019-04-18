package com.example.yyw.mongodb.controller;

import com.example.yyw.mongodb.ResponseData;
import com.example.yyw.mongodb.model.SfyPosOrder;
import com.example.yyw.mongodb.repository.SfyPosOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


/**
 * MongoDB数据库的基本操作
 * */
@RestController
@RequestMapping("/sfyPosOrder")
public class SfyPosOrderController {

    @Autowired
    private SfyPosOrderRepository sfyPosOrderRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/findById")
    public ResponseData findById(@RequestParam("id") String id){
        Optional<SfyPosOrder> sfyPosOrder = sfyPosOrderRepository.findById(id);
        return ResponseData.success(sfyPosOrder.isPresent()?sfyPosOrder.get():null);
    }

    @RequestMapping("/page")
    public ResponseData page(){
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<SfyPosOrder> sfyPosOrder = sfyPosOrderRepository.findAll(pageRequest);
        return ResponseData.success(sfyPosOrder);
    }

    @RequestMapping("/page2")
    public ResponseData page2(@RequestParam("payOrderNo") String payOrderNo){
        Query query = Query.query(Criteria.where("pay_order_no").regex(payOrderNo)).skip(0).limit(10);
        List<SfyPosOrder> sfyPosOrders = mongoTemplate.find(query, SfyPosOrder.class);
        return ResponseData.success(sfyPosOrders);
    }

}
