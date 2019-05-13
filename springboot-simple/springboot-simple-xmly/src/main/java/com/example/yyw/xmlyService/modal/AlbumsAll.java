package com.example.yyw.xmlyService.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * https://open.ximalaya.com/doc/podcast-data-sync#%E8%8E%B7%E5%8F%96%E5%85%A8%E9%87%8F%E4%B8%93%E8%BE%91%E6%95%B0%E6%8D%AE
 * 获取全量专辑数据
 * 接口名称
 * /albums/get_all响应modal
 * @author yanzhitao
 * @date 2018/11/28
 **/
@Data
public class AlbumsAll {
    @JsonProperty("category_id")
    private Long categoryId;
    @JsonProperty("total_page")
    private Long totalPage;
    @JsonProperty("total_count")
    private Long totalCount;
    @JsonProperty("current_page")
    private Long currentPage;
    @JsonProperty("albums")
    private List<Album> albums;

}
