package com.example.service;

import com.example.entity.CommentInfo;

import java.util.List;

/**
 * Created by echo on 17/4/10.
 */
public interface CommentService {
    List<CommentInfo> getGoodsCommentList(String goodsId);
    int addComment(Integer userId, String goodsId, String comment);
}
