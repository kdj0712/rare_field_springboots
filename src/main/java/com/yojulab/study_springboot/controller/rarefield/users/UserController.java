package com.yojulab.study_springboot.controller.rarefield.users;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yojulab.study_springboot.service.sample.UsersService;


@Controller
public class UserController {
    @RequestMapping(value = "/user_login", method = RequestMethod.GET)
    public ModelAndView userLogin(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/users/user_login.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping(value = "/user_join", method = RequestMethod.GET)
    public ModelAndView userJoin(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/users/user_join.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping(value = "/user_infosearch", method = RequestMethod.GET)
    public ModelAndView userInforsearch(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/users/user_infosearch.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }
    
}
