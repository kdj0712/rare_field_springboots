package com.yojulab.study_springboot.controller.rarefield.users;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yojulab.study_springboot.service.rarefield.users.UserService;

@Controller
public class UserInController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/joinUser", method = RequestMethod.POST)
    public ModelAndView joinUser(@RequestParam Map params, ModelAndView modelAndView){
        // 1. 해당 ID 있는지 체크
        boolean isDup = userService.checkDupUser(params);
        if(isDup){
            String viewName = "/WEB-INF/rarefield/views/users/user_join_fail.jsp";
            modelAndView.setViewName(viewName);
            return modelAndView;
        }
        else{
            Object result = userService.insertWithAuth(params);
            String viewName = "/WEB-INF/rarefield/views/mainpage.jsp";
            modelAndView.setViewName(viewName);
            return modelAndView;
        }

        
    }
}
