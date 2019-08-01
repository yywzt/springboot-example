package com.example.springbootelasticsearch.controller;

import com.example.springbootelasticsearch.modal.Customer;
import com.example.springbootelasticsearch.service.CustomerService;
import com.example.yyw.constant.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/15 17:17
 * @describe
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/get/{id}")
    public ResponseData get(@PathVariable("id") String id){
        log.info("log.info");
        log.debug("log.debug");
        log.warn("log.warn");
        log.error("log.error");
        Customer customer = customerService.findById(id);
        return ResponseData.success(customer);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Customer save(@RequestBody @Validated Customer customer){
        return customerService.save(customer);
    }

    @RequestMapping("/selectAll")
    public Object selectAll(){
        return customerService.selectAll();
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ResponseData findAll(){
        List<Customer> customerList = customerService.findAll();
        return ResponseData.success(customerList);
    }

    @RequestMapping(value = "/init",method = RequestMethod.POST)
    public ResponseData init(){
        List<Customer> customerList = new ArrayList<>(1000);
        for(int i=10;i<1000000;i++){
            Customer customer = new Customer();
            customer.setId(String.valueOf(i));
            customer.setName("test"+i);
            customer.setAge(18);
            customerList.add(customer);
        }
        customerService.saveAll(customerList);
        return ResponseData.success();
    }
}
