package com.yojulab.study_springboot.controller.rarefield.rarediseaseinfo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yojulab.study_springboot.service.sample.UsersService;

@Controller
public class RarediseaseInfo {

    @RequestMapping(value = "/info_raredisease", method = RequestMethod.GET)
    public ModelAndView rareDiseaseInfo(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/rarediseaseinfo/info_raredisease.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping(value = "/info_institution", method = RequestMethod.GET)
    public ModelAndView rareDiseaseInstitutions(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/rarediseaseinfo/info_institution.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping(value = "/info_academicinfo", method = RequestMethod.GET)
    public ModelAndView rareDiseaseAcademicinfo(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/rarediseaseinfo/info_academicinfo.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

}
