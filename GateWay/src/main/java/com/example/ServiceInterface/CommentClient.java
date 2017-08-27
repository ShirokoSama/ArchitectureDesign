package com.example.ServiceInterface;

import com.example.entity.CommentInfo;
import com.example.entity.UserInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by echo on 17/3/28.
 */
@FeignClient(value = "comment-service", fallback = CommentClientHystrix.class)
public interface CommentClient {

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    Integer add(@RequestParam(value = "a")Integer a,@RequestParam(value = "b") Integer b);

    @RequestMapping(value = "/testAllUser", method = RequestMethod.GET)
    List<UserInfo> getAllUserInfo();

    @RequestMapping(value = "/testGoodsComment", method = RequestMethod.GET)
    List<CommentInfo> testGoodsCommentInfo(@RequestParam(value = "goodsId") String goodsId);

    @HystrixCommand(fallbackMethod = "", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000")})
    @RequestMapping(value = "/testGoodsComment", method = RequestMethod.POST)
    int testGoodsComment(@RequestParam(value = "goodsId") String goodsId,
                         @RequestParam(value = "userId") int userId,
                         @RequestParam(value = "content") String content);

}
