package com.example.yyw.service;

import com.example.yyw.mapper.ssm.UserInfMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanzhitao@xiaomalixing.com
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

}
