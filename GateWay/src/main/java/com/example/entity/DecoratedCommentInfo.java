package com.example.entity;

/**
 * Created by Srf on 2017/4/13
 */

public class DecoratedCommentInfo{

    Integer id;
    String goodsId;
    Integer userId;
    Integer status;
    String content;
    String userName;

    public DecoratedCommentInfo(CommentInfo info, String userName) {
        this.userName = userName;
        this.id = info.id;
        this.goodsId = info.goodsId;
        this.userId = info.userId;
        this.status = info.status;
        this.content = info.content;
    }

    public String getUserName() {
        return this.userName;
    }

    public Integer getId() {
        return id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
