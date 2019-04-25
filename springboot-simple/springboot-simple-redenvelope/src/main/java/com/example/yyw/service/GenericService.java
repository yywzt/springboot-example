package com.example.yyw.service;

import com.example.yyw.constant.Constants;
import com.example.yyw.model.GenericModel;
import com.example.yyw.util.DateUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/4/25 17:07
 * @describe
 */
@Transactional
public class GenericService<PK> {

    public void initBaseData(GenericModel<PK> genericModel, boolean isUpdate){
        if(isUpdate){
            genericModel.setUpdatedBy(Constants.DEFAULTUPDATEBY);
            genericModel.setUpdationDate(DateUtil.getNowTimestamp());
        }else{
            genericModel.setCreatedBy(Constants.DEFAULTCREATEBY);
            genericModel.setCreationDate(DateUtil.getNowTimestamp());
        }
    }
}
