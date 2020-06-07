package com.example.yyw.simplecat.service;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import org.springframework.stereotype.Service;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/5 11:43
 * @Description
 */
@Service
public class EventService {

    public void pubEvent() {
        Cat.getProducer().logEvent("URL.Server", "serverIp");

        Cat.getProducer().logEvent("URL.Server", "serverIp", Event.SUCCESS, "ip=${serverIp}");
    }

}
