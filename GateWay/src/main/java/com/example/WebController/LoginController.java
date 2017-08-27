package com.example.WebController;

import com.example.ServiceInterface.CommentClient;
import com.example.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Srf on 2017/4/13
 */

@Controller
public class LoginController {

    @Resource
    CommentClient commentClient;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(Model model) {
        return new ModelAndView("/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginVerify(Model model, HttpServletRequest request) {
        String userName = request.getParameter("username");
        List<UserInfo> userInfos = commentClient.getAllUserInfo();
        for(UserInfo info: userInfos){
            if(info.getName().equals(userName))
                return new ModelAndView("redirect:/"+info.getId()+"/search");
        }
        return new ModelAndView("redirect:/login");
    }

}
