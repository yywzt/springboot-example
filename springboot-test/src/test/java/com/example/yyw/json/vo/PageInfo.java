package com.example.yyw.json.vo;

/**
 * @Auther: huojie
 * @Date: 2018/12/4 0004 12:50
 * @Description:
 */
public class PageInfo {

    private int pageNum;//当前页
    private int pageSize;//每页显示的数据条数
    private int totalRecord;//总记录条数
    private int totalPage;//总页数

    public PageInfo(int pageNum, int pageSize, int totalRecord, int totalPage) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
