package com.example.yyw.festival;

/**
 * Created by jiacheng on 2017/12/20 0020.
 * 响应节日的数据
 */
public class FestivalResponse {


    /**
     * {
     *     "status": 200,
     *     "message": "success",
     *     "data": {
     *         "year": 2018,
     *         "month": 11,
     *         "day": 8,
     *         "festivalList": [
     *             "记者节"
     *         ],
     *         "jieqi": {
     *             "8": "立冬",
     *             "22": "小雪"
     *         }
     *     }
     * }
     * year : 2018
     * month : 11
     * day : 08
     * festivalList : ["记者节"]
     * jieqi : {"8": "立冬","22": "小雪"}
     */

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应的节日结果
     */
    private FestivalResult data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FestivalResult getData() {
        return data;
    }

    public void setData(FestivalResult data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FestivalResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
