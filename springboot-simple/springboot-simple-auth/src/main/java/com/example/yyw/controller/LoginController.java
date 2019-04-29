package com.example.yyw.controller;

import com.example.yyw.constants.Constants;
import com.example.yyw.model.ssm.UserInf;
import com.example.yyw.service.UserInfService;
import com.example.yyw.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/29 21:07
 * @describe
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private UserInfService userInfService;

    @RequestMapping(value = "/doLoging",method = RequestMethod.POST)
    public ResponseData doLoging(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password){
        HttpSession session = request.getSession();
        Map<String,Object> sessionIdKey = (Map<String, Object>) session.getAttribute(Constants.SESSION_ID_KEY);
        if(sessionIdKey != null){
            //已登录
            return ResponseData.failure("已登录，请勿重复登录...");
        }
        UserInf userInf = userInfService.getUserInfMapper().findUserInfByUname(username);
        if(userInf != null){
            if(password.equals(userInf.getPasswd())){
                //校验通过  存入session以及redis ，过期时间30分钟
                String sessionId = session.getId();
                session.setMaxInactiveInterval(Constants.SESSION_EXPIRES_MIN*60);
                Map<String,Object> map = new HashMap<>();
                map.put(Constants.SESSION_ID,sessionId);
                map.put(Constants.USER,userInf);
                session.setAttribute(Constants.SESSION_ID_KEY,map);
                return ResponseData.success();
            }else{
                return ResponseData.failure("用户名或密码错误");
            }
        }
        return ResponseData.failure("当前用户为注册啊...");
    }
}
