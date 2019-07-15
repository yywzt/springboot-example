package com.example.springbootelasticsearch.modal;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotEmpty;

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
}
