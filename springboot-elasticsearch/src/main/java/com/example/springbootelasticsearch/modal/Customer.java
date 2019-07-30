package com.example.springbootelasticsearch.modal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/15 17:08
 * @describe
 */
@Data
@Document(indexName = "customer",type = "doc")
public class Customer {

    @Id
    private String id;

    @NotEmpty(message = "name不能为空")
    private String name;

    private Integer age;

    /**
     * 创建人
     */
    protected String createdBy;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date creationDate;

    /**
     * 修改人
     */
    protected String updatedBy;

    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected Date updationDate;

    /**
     * 是否可用
     */
    protected Long enabledFlag = 1L;
}
