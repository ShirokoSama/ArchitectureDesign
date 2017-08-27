package com.example.entity;

import lombok.Data;

/**
 * Created by echo on 17/4/10.
 */
@Data
public class CommentInfo {

    Integer id;
    Integer userId;
    String goodsId;
    String content;
    Integer status;

    public Integer getUserId() {
        return this.userId;
    }

}
