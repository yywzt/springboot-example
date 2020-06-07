package com.example.yyw.simplecat.controller;

import com.example.yyw.simplecat.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/5 11:43
 * @Description
 */
@RestController
@RequestMapping("/cat/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping("/pub")
    public String pub() {
        eventService.pubEvent();
        return "SUCCESS";
    }
}
