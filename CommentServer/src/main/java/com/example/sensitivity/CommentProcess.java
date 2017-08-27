package com.example.sensitivity;

import com.example.dao.CommentRepository;
import com.example.dao.UserRepository;
import com.example.entity.CommentInfo;
import com.example.entity.UserInfo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by echo on 17/4/10.
 */
@Component
public class CommentProcess {

    @Autowired
    CommentAnalysis commentAnalysis;

    @Autowired
    UserAnalysis userAnalysis;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void process(CommentInfo commentInfo){
        if(commentAnalysis.analysis(commentInfo)){
            commentInfo.setStatus(1);
            commentInfo = commentRepository.save(commentInfo);
            System.out.println("评论信息有敏感词汇,已修改标记");
            System.out.println(commentInfo.toString());

            if(userAnalysis.analysis(commentInfo.getUserId())){
                UserInfo userInfo = userRepository.findOne(commentInfo.getUserId());
                userInfo.setStatus(1);
                userInfo = userRepository.save(userInfo);
                System.out.println("该用户已被标记位水军");
                System.out.println(userInfo.toString());
            }
        }
    }
}
