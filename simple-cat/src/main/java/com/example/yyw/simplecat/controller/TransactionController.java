package com.example.yyw.simplecat.controller;

import com.example.yyw.simplecat.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/5 11:49
 * @Description
 */
@RestController
@RequestMapping("/cat/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/pub")
    public String pub() {
        transactionService.pubTransaction();
        return "SUCCESS";
    }
}
