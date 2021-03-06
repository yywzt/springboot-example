package com.example.yyw.xmlyService.modal;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/5/8 14:37
 * @describe 返回专辑更新提醒信息列表
 */
@Data
public class AlbumUpdateBatch {

    /**专辑ID*/
    private Long id;

    /**固定值"album"*/
    private String kind;

    /**专辑下最近更新声音的ID（0表示无更新）*/
    @JsonSetter("last_up_track_id")
    private Long lastUpTrackId;

    /**专辑下最近更新声音的标题*/
    @JsonSetter("last_up_track_title")
    private String lastUpTrackTitle;

    /**专辑下最近更新声音的封面图url*/
    @JsonSetter("last_up_track_cover_path")
    private String lastUpTrackCoverPath;

    /**专辑下最近更新声音的更新时间*/
    @JsonSetter("last_up_track_at")
    private Long lastUpTrackAt;

}
