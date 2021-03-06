package com.example.yyw.task;

import lombok.Data;

import java.util.List;

/**
 * @Author yanzhitao@xiaomalixing.com
 * @Date 2020/6/24 18:41
 * @Description
 */
@Data
public class DataBeanX {
    /**
     * current_page : 1
     * data : [{"unique_id":"18e98f5276b2c1a57f3072d24c0df6a8","ip":"1.255.48.197","port":"8080","country":"韩国","ip_address":"韩国 首尔特别市 首尔特别市","anonymity":1,"protocol":"http","isp":"NBP","speed":799,"validated_at":"2020-06-24 18:35:37","created_at":"2020-06-24 07:23:49","updated_at":"2020-06-24 18:35:37"},{"unique_id":"483bc84828fe69f500c526701756ca41","ip":"101.37.118.54","port":"8888","country":"中国","ip_address":"中国 浙江省 杭州","anonymity":2,"protocol":"http","isp":"Hangzhou Alibaba Adv","speed":84,"validated_at":"2020-06-24 18:35:34","created_at":"2020-06-23 23:42:46","updated_at":"2020-06-24 18:35:34"},{"unique_id":"1f7f8f665d2829366ca0bc846d948043","ip":"101.4.136.34","port":"81","country":"中国","ip_address":"中国 北京市 海淀","anonymity":2,"protocol":"http","isp":"China Education and ","speed":103,"validated_at":"2020-06-24 18:35:18","created_at":"2020-06-23 22:54:11","updated_at":"2020-06-24 18:35:18"},{"unique_id":"a0d84f3e34c5eb5183247e6a717e8a2d","ip":"103.121.149.46","port":"3128","country":"印度尼西亚","ip_address":"印度尼西亚 Jakarta 雅加达","anonymity":2,"protocol":"http","isp":"PT EMERIO INDONESIA","speed":1126,"validated_at":"2020-06-24 18:31:29","created_at":"2020-06-24 18:31:29","updated_at":"2020-06-24 18:31:34"},{"unique_id":"3f31cd63d3781823b1721f5b9112dadc","ip":"103.146.203.237","port":"3128","country":"印尼","ip_address":"印尼 Jakarta 雅加达","anonymity":2,"protocol":"http","isp":"PT Cloud Hosting Ind","speed":2612,"validated_at":"2020-06-24 18:35:06","created_at":"2020-06-24 17:49:05","updated_at":"2020-06-24 18:35:06"},{"unique_id":"3714ac75d37a5209e05acc1a00adf1ac","ip":"103.216.51.210","port":"8191","country":"柬埔寨","ip_address":"柬埔寨 Phnom Penh 金边","anonymity":2,"protocol":"http","isp":"Today Communication ","speed":2603,"validated_at":"2020-06-24 18:35:53","created_at":"2020-06-24 00:03:18","updated_at":"2020-06-24 18:35:53"},{"unique_id":"615279825c40751b360530ec693a1005","ip":"103.224.195.41","port":"3128","country":"台湾","ip_address":"台湾 臺灣省 or 台灣省 糞箕湖","anonymity":1,"protocol":"http","isp":"TUNGHO","speed":175,"validated_at":"2020-06-24 18:35:40","created_at":"2020-06-24 07:25:16","updated_at":"2020-06-24 18:35:40"},{"unique_id":"0c4a509a8b7be62e5a505cb7e2c9ab10","ip":"103.235.46.121","port":"80","country":"香港","ip_address":"香港 Central and Western District Central","anonymity":2,"protocol":"http","isp":"Beijing Baidu Netcom","speed":437,"validated_at":"2020-06-24 18:35:49","created_at":"2020-06-11 17:10:33","updated_at":"2020-06-24 18:35:49"},{"unique_id":"6006d903ba3c07dae65245a365635b34","ip":"103.235.46.154","port":"80","country":"中国","ip_address":"中国 香港 ","anonymity":2,"protocol":"http","isp":"百度","speed":531,"validated_at":"2020-06-24 18:35:26","created_at":"2020-06-16 15:59:16","updated_at":"2020-06-24 18:35:26"},{"unique_id":"70efd540ec231649ac2745b0b00e08d1","ip":"103.89.24.4","port":"80","country":"孟加拉","ip_address":"孟加拉 ","anonymity":2,"protocol":"http","isp":"","speed":449,"validated_at":"2020-06-24 18:35:27","created_at":"2020-06-24 18:00:22","updated_at":"2020-06-24 18:35:27"},{"unique_id":"398ed6170947c5d0949628ae79d54d19","ip":"104.207.147.141","port":"8080","country":"美国","ip_address":"美国 佛罗里达州 迈阿密","anonymity":1,"protocol":"http","isp":"Choopa","speed":1682,"validated_at":"2020-06-24 18:35:45","created_at":"2020-06-24 15:31:01","updated_at":"2020-06-24 18:35:45"},{"unique_id":"dbbabe46c89c8ba83c62f3bbab1255f8","ip":"105.112.8.53","port":"3128","country":"尼日利亚","ip_address":"尼日利亚 Lagos ","anonymity":1,"protocol":"http","isp":"印度电信","speed":2346,"validated_at":"2020-06-24 18:35:38","created_at":"2020-06-24 17:55:14","updated_at":"2020-06-24 18:35:38"},{"unique_id":"a7b1bee12c5071aa12c08f2d20f50dd7","ip":"111.13.100.91","port":"80","country":"中国","ip_address":"中国 广东 深圳市","anonymity":2,"protocol":"http","isp":"China Mobile communi","speed":17,"validated_at":"2020-06-24 18:35:38","created_at":"2020-04-27 20:21:44","updated_at":"2020-06-24 18:35:38"},{"unique_id":"cf0a4c83745947847859ba15d315a599","ip":"111.206.37.100","port":"80","country":"中国","ip_address":"中国 北京市 北京","anonymity":2,"protocol":"http","isp":"China Unicom Beijing","speed":19,"validated_at":"2020-06-24 18:35:38","created_at":"2020-05-02 06:43:32","updated_at":"2020-06-24 18:35:38"},{"unique_id":"66feeb2c469086555e61011bcb6abbf6","ip":"111.206.37.161","port":"80","country":"中国","ip_address":"中国 北京 北京市","anonymity":2,"protocol":"http","isp":"联通","speed":24,"validated_at":"2020-06-24 18:35:40","created_at":"2020-04-28 14:54:48","updated_at":"2020-06-24 18:35:40"}]
     * first_page_url : https://ip.jiangxianli.com/api/proxy_ips?page=1
     * from : 1
     * last_page : 18
     * last_page_url : https://ip.jiangxianli.com/api/proxy_ips?page=18
     * next_page_url : https://ip.jiangxianli.com/api/proxy_ips?page=2
     * path : https://ip.jiangxianli.com/api/proxy_ips
     * per_page : 15
     * prev_page_url : null
     * to : 15
     * total : 270
     */

    private Integer current_page;
    private String first_page_url;
    private Integer from;
    private Integer last_page;
    private String last_page_url;
    private String next_page_url;
    private String path;
    private Integer per_page;
    private Object prev_page_url;
    private Integer to;
    private Integer total;
    private List<DataBean> data;

}
