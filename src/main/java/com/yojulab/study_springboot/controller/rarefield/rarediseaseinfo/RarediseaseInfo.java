package com.yojulab.study_springboot.controller.rarefield.rarediseaseinfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.yojulab.study_springboot.service.rarefield.rest.RestTemplateService;

import com.yojulab.study_springboot.service.sample.UsersService;

@RestController
@RequestMapping("/info")
public class RarediseaseInfo {

    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping(value = "/info_raredisease")
        public ModelAndView Dise_Info(ModelAndView modelAndView){
        Map<String,Object> result = restTemplateService.infodiseaseRequest(currentPage);
        String viewName = "/WEB-INF/rarefield/views/info/info_raredisease.jsp";
        modelAndView.setViewName(viewName);
        modelAndView.addObject("result", result);
        return modelAndView;
    }

    @RequestMapping(value = "/info_institution", method = RequestMethod.GET)
    public ModelAndView rareDiseaseInstitutions(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/info/info_institution.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @RequestMapping(value = "/info_academicinfo", method = RequestMethod.GET)
    public ModelAndView rareDiseaseAcademicinfo(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/info/info_academicinfo.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

}
