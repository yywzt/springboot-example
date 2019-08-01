package com.example.springbootelasticsearch.modal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/8/1 10:47
 * @describe
 */
@Data
@Document(indexName = "student", type = "jdbc")
public class Student {

    @Id
    private String id;

    @NotEmpty(message = "name不能为空")
    private String name;

    private Integer age;
    private String gender;

    /**
     * 创建人
     */
//    @JsonProperty("createdBy")
//    protected String created_by;
    @JsonProperty("created_by")
    protected String createdBy;

    /**
     * 创建日期
     */
    @JsonProperty("creation_date")
    protected Date creationDate;
//    @JsonProperty("creationDate")
//    protected Date creation_date;

    /**
     * 修改人
     */
    @JsonProperty("updated_by")
    protected String updatedBy;
//    @JsonProperty("updatedBy")
//    protected String updated_by;

    /**
     * 修改日期
     */
    @JsonProperty("updation_date")
    protected Date updationDate;
//    @JsonProperty("updationDate")
//    protected Date updation_date;

    /**
     * 是否可用
     */
    @JsonProperty("enabled_flag")
    protected String enabledFlag;
//    @JsonProperty("enabledFlag")
//    protected String enabled_flag;
}
