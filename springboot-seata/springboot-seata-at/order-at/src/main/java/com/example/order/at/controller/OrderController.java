package com.example.order.at.controller;

import com.example.order.at.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.example.order.at.service.OrderService.FAIL;
import static com.example.order.at.service.OrderService.SUCCESS;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = "application/json")
    public String create(String userId, String commodityCode, int orderCount) {
        try {
            orderService.create(userId, commodityCode, orderCount);
        } catch (Exception exx) {
            exx.printStackTrace();
            return FAIL;
        }
        return SUCCESS;
    }

}
