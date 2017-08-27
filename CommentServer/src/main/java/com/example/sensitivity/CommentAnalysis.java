package com.example.sensitivity;

import com.example.dao.SensitiveRepository;
import com.example.entity.CommentInfo;
import com.example.entity.SensitiveInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 17/4/10.
 */
@Component
public class CommentAnalysis {

    @Autowired
    SensitiveRepository sensitiveRepository;

    private List<String> sensitiveList;

    public boolean analysis(CommentInfo commentInfo){

        if(sensitiveList == null){
            initSensitiveList();
        }

        boolean result = false;
        List<String> sensitiveList = getSensitiveList();
        String comment = commentInfo.getContent();
        for(String sensitive: sensitiveList){
            if(comment.contains(sensitive)){
                result = true;
                break;
            }
        }
        return result;
    }

    private void initSensitiveList(){
        sensitiveList = getSensitiveList();
    }

    private List<String> getSensitiveList(){
        List<String> sensitiveList = new ArrayList<>();
        List<SensitiveInfo> list = sensitiveRepository.findAll();
        for(SensitiveInfo sensitiveInfo: list){
            sensitiveList.add(sensitiveInfo.getWords());
        }
        return sensitiveList;
    }
}
