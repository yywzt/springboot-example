package com.example.yyw.springbootkafka.message;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yanzhitao@xiaomalixing.com
 * @date 2019/7/9 19:19
 * @describe
 */
@Data
public class Message implements Serializable {

    private Long id;
    private String msg;
    private LocalDateTime sengTime;
}
