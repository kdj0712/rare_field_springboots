package com.yojulab.study_springboot.controller.rarefield.otherqna;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yojulab.study_springboot.service.sample.UsersService;


@Controller
public class OtherQna {
    @RequestMapping(value = "/other_QnA_main", method = RequestMethod.GET)
    public ModelAndView otherQnaMain(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/other/other_QnA_main.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping(value = "/other_notice", method = RequestMethod.GET)
    public ModelAndView otherQnaNotice(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/other/other_notice.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

}
