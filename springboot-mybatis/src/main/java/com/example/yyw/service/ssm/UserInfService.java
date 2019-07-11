package com.example.yyw.service.ssm;

import com.alibaba.fastjson.JSONObject;
import com.example.yyw.mapper.ssm.UserInfMapper;
import com.example.yyw.model.ssm.UserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/7/11 21:40
 * @describe
 */
@Service
public class UserInfService {

    @Autowired
    private UserInfMapper userInfMapper;

    /**
     * @param sort ["id,asc","uname,desc"]
     * @return
     */
    public List<UserInf> test_sort_1(String sort){
        List<String> strings = JSONObject.parseArray(sort, String.class);
        StringBuffer stringBuffer = new StringBuffer();
        strings.stream().forEach(s -> {
            String[] split = s.split(",");
            String properties = split[0];
            String direction = split[1];
            stringBuffer.append(properties).append(" ").append(direction).append(",");
        });
        String str = stringBuffer.toString();
        String sortString = str.substring(0, str.length() - 1);
        return userInfMapper.findUserInfByUnameAndPasswdAndSort(1, sortString);
    }
}
