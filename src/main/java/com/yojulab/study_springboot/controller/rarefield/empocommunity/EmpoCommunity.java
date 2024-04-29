package com.yojulab.study_springboot.controller.rarefield.empocommunity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yojulab.study_springboot.service.UsersService;

@Controller
public class EmpoCommunity {
    
    @RequestMapping(value = "/empo_community", method = RequestMethod.GET)
    public ModelAndView rareDiseaseInfo(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/empocommunity/empo_community.jsp";
        
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping(value = "/empo_program", method = RequestMethod.GET)
    public ModelAndView rareDiseaseInstitutions(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/empocommunity/empo_program.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

}
