package com.example.serviceImpl;

import com.example.dao.CommentRepository;
import com.example.entity.CommentInfo;
import com.example.sensitivity.CommentAnalysis;
import com.example.sensitivity.CommentProcess;
import com.example.sensitivity.UserAnalysis;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 17/4/10.
 */
@Component
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentProcess commentProcess;

    @Autowired
    CommentAnalysis commentAnalysis;

    @Autowired
    UserAnalysis userAnalysis;

    @Override
    public List<CommentInfo> getGoodsCommentList(String goodsId) {
        List<CommentInfo> commentInfoList = commentRepository.findByGoodsId(goodsId);
        List<CommentInfo> result = new ArrayList<>();
        for(CommentInfo commentInfo: commentInfoList){
            if(!commentAnalysis.analysis(commentInfo)){
                result.add(commentInfo);
            }
        }

        return result;
    }

    @Override
    @Transactional
    public int addComment(Integer userId, String goodsId, String comment) {

        if(userAnalysis.analysis(userId)){
            System.out.println("水军不能评论");
            return 0;
        }

        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setContent(comment);
        commentInfo.setStatus(0);
        commentInfo.setGoodsId(goodsId);
        commentInfo.setUserId(userId);

        try{
            commentInfo = commentRepository.save(commentInfo);
            commentProcess.process(commentInfo);

            System.out.println(commentInfo.toString());
            return 1;
        }catch (Exception e){
            System.out.println("something error");
            return 0;
        }
    }
}
