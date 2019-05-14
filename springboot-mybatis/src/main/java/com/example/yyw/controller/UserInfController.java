package com.example.yyw.controller;

import com.example.yyw.config.Student;
import com.example.yyw.mapper.ssm.UserInfMapper;
import com.example.yyw.model.ssm.UserInf;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @RequestMapping("/findAll")
    public List<UserInf> findAll(){
        log.info("dataSource: {}",primaryDataSource);
        log.info("dataSource: {}",student);
        List<UserInf> all = userInfMapper.selectAll();
        log.info("userinf: {}",all);
        return all;
    }
    @RequestMapping("/findUserInfByUname")
    public UserInf findUserInfByUname(String username){
        UserInf all = userInfMapper.findUserInfByUname(username);
        log.info("userinf: {}",all);
        return all;
    }
}
