package com.example.yyw.service.xmly;

import com.example.yyw.mapper.ssm.BasedMapper;
import com.example.yyw.service.ssm.LogService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/5/20 15:13
 * @describe
 */
@Slf4j
@Service
public class XmlyService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BasedMapper basedMapper;

    @Autowired
    private LogService logService;

    @Transactional
    public int[] saveXmlyCategory(){
        String sql = "INSERT INTO `ssm`.`xmly_category` (`name`, `kind`, `origin_id`, `cover_url_large`, `cover_url_middle`, `cover_url_small`, `order_no`, `create_date`, `modify_date`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Date date = new Date();
        List<Object[]> batchArgs = Lists.newArrayList();
        batchArgs.add(new Object[]{"有声书","category",3L,"http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVPxiPpMAAAMRgI-yqM007.jpg","http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVPxiPpMAAAMRgI-yqM007.jpg","http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVTTQ1EOAAAMRgI-yqM098.jpg","2",date,date,0});
        batchArgs.add(new Object[]{"音乐","category",2L,"http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVPxiPpMAAAMRgI-yqM007.jpg","http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVPxiPpMAAAMRgI-yqM007.jpg","http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVTTQ1EOAAAMRgI-yqM098.jpg","3",date,date,0});
        batchArgs.add(new Object[]{"娱乐","category",4L,"http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVPxiPpMAAAMRgI-yqM007.jpg","http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVPxiPpMAAAMRgI-yqM007.jpg","http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVTTQ1EOAAAMRgI-yqM098.jpg","4",date,date,0});
        batchArgs.add(new Object[]{"相声评书","category",12L,"http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVPxiPpMAAAMRgI-yqM007.jpg","http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVPxiPpMAAAMRgI-yqM007.jpg","http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVTTQ1EOAAAMRgI-yqM098.jpg","5",date,date,0});
        batchArgs.add(new Object[]{"儿童","category",6L,"http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVPxiPpMAAAMRgI-yqM007.jpg","http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVPxiPpMAAAMRgI-yqM007.jpg","http://fdfs.xmcdn.com/group31/M05/84/25/wKgJSVl2sVTTQ1EOAAAMRgI-yqM098.jpg","6",date,date,0});
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        log.info("{}",ints);
        return ints;
    }

    public void alterTable(){
        String newTableName = "log_bak_201905201620";
        //中间临时表
        String temporaryTableName = "log_bak_temp";
        String sourceTableName = "log";

        //创建中间临时表
        basedMapper.dropTable(temporaryTableName);
        basedMapper.createTable(temporaryTableName, sourceTableName);

        try {
            //        saveLogs(temporaryTableName);
            logService.batchSaveLogs(temporaryTableName);
        }finally {
            basedMapper.dropTable(newTableName);
            basedMapper.alterTableName(sourceTableName, newTableName);
            basedMapper.dropTable(sourceTableName);
            basedMapper.alterTableName(temporaryTableName, sourceTableName);
        }
    }

}
