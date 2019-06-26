package com.example.yyw.controller;

import com.example.yyw.model.ssm.Log;
import com.example.yyw.service.ssm.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/6/26 23:22
 * @describe
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/selectById")
    public Object selectById(Long id){
        return logService.getLogMapper().selectByPrimaryKey(id);
    }

    @RequestMapping("/selectByCondition")
    public Object selectByCondition(String property){
        Condition condition = new Condition(Log.class);
        condition.createCriteria().andEqualTo("groupName", property);
        return logService.getLogMapper().selectByCondition(condition);
    }
    @RequestMapping("/selectByCondition1")
    public Object selectByCondition1(@RequestParam String property, @RequestParam List<Long> ids){
        Condition condition = new Condition(Log.class);
        condition.createCriteria()
                .andEqualTo("groupName", property)
                .andIn("id", ids);
        return logService.getLogMapper().selectByCondition(condition);
    }
}
