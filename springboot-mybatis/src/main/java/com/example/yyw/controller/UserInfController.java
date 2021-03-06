package com.example.yyw.controller;

import com.example.yyw.config.Student;
import com.example.yyw.constant.ResponseData;
import com.example.yyw.mapper.ssm.UserInfMapper;
import com.example.yyw.model.ssm.UserInf;
import com.example.yyw.service.ssm.UserInfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author yanzt
 * @date 2019/4/20 15:39
 * @describe
 */
@RestController
@RequestMapping("/userinf")
@Slf4j
public class UserInfController {

    @Autowired
    private DataSource primaryDataSource;

    @Autowired
    private Student student;

    @Autowired
    private UserInfMapper userInfMapper;
    @Autowired
    private UserInfService userInfService;

    @RequestMapping("/findAll")
    public List<UserInf> findAll(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("dataSource: {}",primaryDataSource);
        log.info("dataSource: {}",student);
        List<UserInf> all = userInfMapper.selectAll();
        log.info("userinf: {}",all);
        return all;
    }
    @RequestMapping("/save")
    public ResponseData save(@RequestBody UserInf userInf){
        log.info("userInf: {}",userInf);
        return ResponseData.success();
    }
    @RequestMapping("/findUserInfByUname")
    public UserInf findUserInfByUname(String username){
        UserInf all = userInfMapper.findUserInfByUname(username);
        log.info("userinf: {}",all);
        return all;
    }

    @RequestMapping("/testSort1")
    public List<UserInf> test_sort_1(String sort){
        return userInfService.test_sort_1(sort);
    }
}
