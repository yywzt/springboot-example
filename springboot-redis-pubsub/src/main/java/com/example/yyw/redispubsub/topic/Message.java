package com.example.yyw.redispubsub.topic;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ywyw2424@foxmail.com
 * @date 2018/9/28 17:17
 * @desc
 */
@Data
public class Message implements Serializable {

    private Long id;
    private String msg;
    private LocalDateTime sengTime;

    public Message(Long id, String msg, LocalDateTime sengTime) {
        this.id = id;
        this.msg = msg;
        this.sengTime = sengTime;
    }
}
