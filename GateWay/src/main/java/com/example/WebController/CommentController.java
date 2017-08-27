package com.example.WebController;

import com.example.ServiceInterface.CommentClient;
import com.example.ServiceInterface.SearchClient;
import com.example.entity.CommentInfo;
import com.example.entity.DecoratedCommentInfo;
import com.example.entity.GoodsInfo;
import com.example.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srf on 2017/4/12
 */

@Controller
public class CommentController {

    @Resource
    private CommentClient commentClient;
    @Resource
    private SearchClient searchClient;

    @RequestMapping(value = "/{id}/info/{gid}", method = RequestMethod.GET)
    public ModelAndView getDetailInfoPage(Model model, @PathVariable("id") int uid,
                                          @PathVariable("gid") String gid) {
        GoodsInfo goodsInfo = searchClient.getGoodsInfo(gid);
        List<CommentInfo> commentInfos = commentClient.testGoodsCommentInfo(gid);
        model.addAttribute("returnInfo", 1);
        model.addAttribute("goodsInfo", goodsInfo);
        model.addAttribute("userInfo", getUserInfo(uid));
        model.addAttribute("commentInfos", decorateCommentInfos(commentInfos));
        return new ModelAndView("/info");
    }

    @RequestMapping(value = "/{id}/info/{gid}/{result}", method = RequestMethod.GET)
    public ModelAndView getDetailInfoPage2(Model model, @PathVariable("id") int uid,
        @PathVariable("gid") String gid, @PathVariable("result") int result) {
        GoodsInfo goodsInfo = searchClient.getGoodsInfo(gid);
        List<CommentInfo> commentInfos = commentClient.testGoodsCommentInfo(gid);
        model.addAttribute("returnInfo", result);
        model.addAttribute("goodsInfo", goodsInfo);
        model.addAttribute("userInfo", getUserInfo(uid));
        model.addAttribute("commentInfos", decorateCommentInfos(commentInfos));
        return new ModelAndView("/info");
    }

    @RequestMapping(value = "/{id}/comment/{gid}", method = RequestMethod.POST)
    public ModelAndView makeComment(Model model, @PathVariable("id") int uid,
        @PathVariable("gid") String gid, HttpServletRequest request) {
        String content = request.getParameter("content");
        int returnInfo = commentClient.testGoodsComment(gid, uid, content);
        GoodsInfo goodsInfo = searchClient.getGoodsInfo(gid);
        List<CommentInfo> commentInfos = commentClient.testGoodsCommentInfo(gid);
        return new ModelAndView("redirect:/"+uid+"/info/"+gid+"/"+returnInfo);
    }

    private UserInfo getUserInfo(int uid) {
        List<UserInfo> userInfos = commentClient.getAllUserInfo();
        for(int i=0;i<userInfos.size();i++){
            if(userInfos.get(i).getId()==uid)
                return userInfos.get(i);
        }
        return null;
    }

    private List<DecoratedCommentInfo> decorateCommentInfos(List<CommentInfo> commentInfos) {
        List<DecoratedCommentInfo> decoratedCommentInfos = new ArrayList<>();
        for(CommentInfo info: commentInfos)
            decoratedCommentInfos.add(new DecoratedCommentInfo(info, getUserInfo(info.getUserId()).getName()));
        return decoratedCommentInfos;
    }

}
