package com.example.WebController;

import com.example.ServiceInterface.CommentClient;
import com.example.ServiceInterface.SearchClient;
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
public class SearchController {

    @Resource
    SearchClient searchClient;
    @Resource
    CommentClient commentClient;

    @RequestMapping(value = "/{id}/search", method = RequestMethod.GET)
    public ModelAndView getSearchPage(Model model, @PathVariable("id") int id) {
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        model.addAttribute("id",id);
        model.addAttribute("userInfo",getUserInfo(id));
        model.addAttribute("goodsInfo", goodsInfos);
        model.addAttribute("key", "");
        model.addAttribute("sort", "general");
        return new ModelAndView("/search");
    }

    @RequestMapping(value = "/{id}/search", method = RequestMethod.POST)
    public ModelAndView getSearchResultPage(Model model, HttpServletRequest request,
        @PathVariable("id") int id){
        String key = request.getParameter("key");
        List<GoodsInfo> goodsInfos = searchClient.testES(key);
        model.addAttribute("id",id);
        model.addAttribute("userInfo",getUserInfo(id));
        model.addAttribute("goodsInfo", goodsInfos);
        model.addAttribute("key", key);
        model.addAttribute("sort", "general");
        return new ModelAndView("/search");
    }

    @RequestMapping(value = "/{id}/search/{key}/{sort}", method = RequestMethod.GET)
    public ModelAndView getSortResultPage(Model model, @PathVariable("key") String key,
        @PathVariable("sort") String sort, @PathVariable("id") int id){
        List<String> sorts = new ArrayList<>();
        List<GoodsInfo> goodsInfos = null;
        if(sort.equals("source")||sort.equals("name")||sort.equals("price")) {
            sorts.add(sort);
            goodsInfos = searchClient.testESFilter(key,sorts);
        }
        else if(sort.equals("general")){
            goodsInfos = searchClient.testES(key);
        }
        else
            goodsInfos = new ArrayList<>();
        model.addAttribute("id",id);
        model.addAttribute("userInfo",getUserInfo(id));
        model.addAttribute("goodsInfo", goodsInfos);
        model.addAttribute("key", key);
        model.addAttribute("sort", sort);
        return new ModelAndView("/search");
    }

    private UserInfo getUserInfo(int uid) {
        List<UserInfo> userInfos = commentClient.getAllUserInfo();
        for(int i=0;i<userInfos.size();i++){
            if(userInfos.get(i).getId()==uid)
                return userInfos.get(i);
        }
        return null;
    }

}
