package com.example.yyw.task;

import lombok.Data;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/24 18:41
 * @Description
 */
@Data
public class DataBean {
    /**
     * unique_id : 18e98f5276b2c1a57f3072d24c0df6a8
     * ip : 1.255.48.197
     * port : 8080
     * country : 韩国
     * ip_address : 韩国 首尔特别市 首尔特别市
     * anonymity : 1
     * protocol : http
     * isp : NBP
     * speed : 799
     * validated_at : 2020-06-24 18:35:37
     * created_at : 2020-06-24 07:23:49
     * updated_at : 2020-06-24 18:35:37
     */

    private String unique_id;
    private String ip;
    private Integer port;
    private String country;
    private String ip_address;
    private Integer anonymity;
    private String protocol;
    private String isp;
    private Integer speed;
    private String validated_at;
    private String created_at;
    private String updated_at;

}
