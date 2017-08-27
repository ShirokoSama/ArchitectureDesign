package com.example.ServiceInterface;

import com.example.entity.CommentInfo;
import com.example.entity.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 17/4/10.
 */
@Component
public class CommentClientHystrix implements CommentClient {
    @Override
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return -1;
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        return new ArrayList<>();
    }

    @Override
    public List<CommentInfo> testGoodsCommentInfo(@RequestParam(value = "goodsId") String goodsId) {
        return new ArrayList<>();
    }

    @Override
    public int testGoodsComment(@RequestParam(value = "goodsId") String goodsId, @RequestParam(value = "userId") int userId, @RequestParam(value = "content") String content) {
        return -99999;
    }
}
