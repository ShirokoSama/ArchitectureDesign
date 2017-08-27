package com.example.WebController;

import com.example.dao.CommentRepository;
import com.example.dao.UserRepository;
import com.example.entity.CommentInfo;
import com.example.entity.UserInfo;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by echo on 17/2/22.
 */
@RestController
public class ComputeController {
    private final Logger logger = Logger.getLogger(getClass().toString());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private DiscoveryClient client;


    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }

    @RequestMapping(value = "/testAllUser", method = RequestMethod.GET)
    public List<UserInfo> testAllUserInfo(){
        return userRepository.findAll();
    }


    @RequestMapping(value = "/testGoodsComment", method = RequestMethod.GET)
    public List<CommentInfo> testGoodsCommentInfo(@RequestParam String goodsId){
        return commentService.getGoodsCommentList(goodsId);
    }

    @RequestMapping(value = "/testGoodsComment", method = RequestMethod.POST)
    public int testGoodsComment(@RequestParam String goodsId, @RequestParam int userId, @RequestParam String content){
        return commentService.addComment(userId, goodsId, content);
    }
}
