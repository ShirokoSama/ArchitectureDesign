package com.example.sensitivity;

import com.example.dao.CommentRepository;
import com.example.entity.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by echo on 17/4/10.
 */
@Component
public class UserAnalysis {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentAnalysis commentAnalysis;

    public boolean analysis(Integer userId){
        List<CommentInfo> userComment = commentRepository.findByUserId(userId);

        int counter=0;

        for(CommentInfo commentInfo: userComment){
            if(commentAnalysis.analysis(commentInfo)){
                counter++;
            }
        }

        if(counter>=5){
            return true;
        }else{
            return false;
        }
    }
}
