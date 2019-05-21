package com.example.yyw.xmlyService.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/10 16:44
 * @describe
 */
@Data
public class LastUpTrack {

    /** 最新上传声音ID */
    @JsonProperty("track_id")
    private Long trackId;
    /** 最新上传声音标题 */
    @JsonProperty("track_title")
    private String trackTitle;
    /** 最新上传声音时长，单位为秒 */
    @JsonProperty("duration")
    private Long duration;
    /** 最新上传声音更新时间，Unix毫秒数时间戳 */
    @JsonProperty("updated_at")
    private Long updatedAt;
    /** 最新上传声音创建时间，Unix毫秒数时间戳 */
    @JsonProperty("created_at")
    private Long createdAt;

}
