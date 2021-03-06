package com.example.yyw.service;

import com.example.yyw.mapper.ssm.UserInfMapper;
import com.example.yyw.model.ssm.UserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/4/29 21:10
 * @describe
 */
@Service
public class UserInfService {

    @Autowired
    private UserInfMapper userInfMapper;

    public UserInfMapper getUserInfMapper() {
        return userInfMapper;
    }

    public UserInf getUserInf(String username){
        return getUserInfMapper().findUserInfByUname(username);
    }

}
