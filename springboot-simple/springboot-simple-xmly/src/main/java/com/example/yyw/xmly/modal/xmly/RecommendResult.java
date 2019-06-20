package com.example.yyw.xmly.modal.xmly;

import java.util.Date;

public class RecommendResult {
    private Long id;
    private Date createDate;
    private Date modifyDate;

    private String channelId;
    private String userId;

    private String itemType;
    private String itemCategory;
    private String itemId;

    private Double score;
    private Integer status;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Date getCreateDate(){
        return createDate;
    }

    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public Date getModifyDate(){
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate){
        this.modifyDate = modifyDate;
    }

    public String getChannelId(){
        return channelId;
    }

    public void setChannelId(String channelId){
        this.channelId = channelId;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getItemType(){
        return itemType;
    }

    public void setItemType(String itemType){
        this.itemType = itemType;
    }

    public String getItemCategory(){
        return itemCategory;
    }

    public void setItemCategory(String itemCategory){
        this.itemCategory = itemCategory;
    }

    public String getItemId(){
        return itemId;
    }

    public void setItemId(String itemId){
        this.itemId = itemId;
    }

    public Double getScore(){
        return score;
    }

    public void setScore(Double score){
        this.score = score;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    @Override
    public String toString(){
        return "RecommendResult{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", channelId='" + channelId + '\'' +
                ", userId='" + userId + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", itemId='" + itemId + '\'' +
                ", score=" + score +
                ", status=" + status +
                '}';
    }
}
