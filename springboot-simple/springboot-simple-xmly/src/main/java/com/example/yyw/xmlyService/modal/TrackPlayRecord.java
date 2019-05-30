package com.example.yyw.xmlyService.modal;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * 喜马拉雅歌曲播放记录对象
 *
 * @author yanzhitao
 **/
@Data
public class TrackPlayRecord{
    @JsonSetter("track_id")
    private Integer trackId;
    @JsonSetter("duration")
    private Integer duration;
    @JsonSetter("played_secs")
    private Integer playedSecs;
    @JsonSetter("started_at")
    private Integer startedAt;
    @JsonSetter("play_type")
    private Integer playType;

}
