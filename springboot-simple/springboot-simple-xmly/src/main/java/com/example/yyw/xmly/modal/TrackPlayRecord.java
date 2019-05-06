package com.example.yyw.xmly.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 喜马拉雅歌曲播放记录对象
 *
 * @author buxianglong
 **/
@Data
public class TrackPlayRecord{
    @JsonProperty("track_id")
    private Integer trackId;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("played_secs")
    private Integer playedSecs;
    @JsonProperty("started_at")
    private Integer startedAt;
    @JsonProperty("play_type")
    private Integer playType;

}
