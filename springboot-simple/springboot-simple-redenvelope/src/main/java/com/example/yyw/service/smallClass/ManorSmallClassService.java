package com.example.yyw.service.smallClass;

import com.example.yyw.mapper.smallClass.ManorSmallClassMapper;
import com.example.yyw.model.smallClass.ManorSmallClass;
import com.example.yyw.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/29 11:49
 * @describe
 */
@Slf4j
@Service
public class ManorSmallClassService extends GenericService<Long> {

    @Autowired
    private ManorSmallClassMapper manorSmallClassMapper;

    public ManorSmallClass getManorSmallClass(){
        int id = RandomUtils.nextInt(1, 6);
        ManorSmallClass manorSmallClass = manorSmallClassMapper.selectByPrimaryKey(id);
        return manorSmallClass;
    }

}
