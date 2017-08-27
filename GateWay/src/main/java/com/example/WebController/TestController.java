package com.example.WebController;

import com.example.ServiceInterface.CommentClient;
import com.example.ServiceInterface.SearchClient;
import com.example.entity.CommentInfo;
import com.example.entity.GoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by echo on 17/3/28.
 */
@Controller
public class TestController {

    @Autowired
    CommentClient commentClient;

    @Autowired
    SearchClient searchClient;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String commentAdd(Model model) {
        model.addAttribute("result",commentClient.add(1,3));
        return "index";
    }

    @RequestMapping(value = "/searchTest", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsInfo> searchTest(@RequestParam(value = "key")String key) {
        return searchClient.testES(key);
    }

    @RequestMapping(value = "/searchServer", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsInfo> searchAdd(@RequestParam(value = "key")String key,@RequestParam(value = "filterStrategy") List<String> filterStrategy) {
        return searchClient.testESFilter(key, filterStrategy);
    }

    @RequestMapping(value = "/goodsInfo", method = RequestMethod.GET)
    @ResponseBody
    public GoodsInfo getGoodsInfo(@RequestParam(value = "goodsId")String goodsId){
        return searchClient.getGoodsInfo(goodsId);
    }

    @RequestMapping(value = "/commentServer", method = RequestMethod.GET)
    @ResponseBody
    public List<CommentInfo> commentInfoTest(@RequestParam(value = "goodsId")String goodsId){
        return commentClient.testGoodsCommentInfo(goodsId);
    }


    @RequestMapping(value = "/commentTest", method = RequestMethod.POST)
    @ResponseBody
    public int commentTest(@RequestParam(value = "goodsId")String goodsId,
                           @RequestParam(value = "userId")int userId,
                           @RequestParam(value = "content")String content){
        return commentClient.testGoodsComment(goodsId, userId, content);
    }
}
