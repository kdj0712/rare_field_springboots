package com.yojulab.study_springboot.controller.rarefield.rarediseaseinfo;

import java.util.HashMap;
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
import com.yojulab.study_springboot.service.rarefield.rest.RestTemplateService.MapQueryService;
import com.yojulab.study_springboot.service.sample.UsersService;

@Controller
@RequestMapping("/info")
public class RarediseaseInfo {
    // public String yourMethod(Model model) {
    //     // result 객체 생성 및 준비
    //     Map<String, Object> result = new HashMap<>();
    //     // result 객체에 데이터 추가
    //     model.addAttribute("result", result);
    //     return "yourViewName";
    // }
    private final MapQueryService mapQueryService;

    @Autowired
    RestTemplateService restTemplateService;

    public RarediseaseInfo(MapQueryService mapQueryService) {
        this.mapQueryService = mapQueryService;
    }

    // @GetMapping(value = "/info_raredisease")
    //     public ModelAndView Dise_Info(ModelAndView modelAndView){
    //     Map<String,Object> result = restTemplateService.infodiseaseRequest();
    //     String viewName = "/WEB-INF/rarefield/views/info/info_raredisease.jsp";
    //     modelAndView.setViewName(viewName);
    //     modelAndView.addObject("result", result);
    //     return modelAndView;
    // }



    @GetMapping(value = "/info_institution")
    public ModelAndView institutionSearch(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "0") double latitude,
            @RequestParam(required = false, defaultValue = "0") double longitude,
            @RequestParam(required = false, defaultValue = "1") int currentPage,
            ModelAndView modelAndView) {
        Map<String, Object> result = mapQueryService.institutionQueryRequest(keyword, latitude, longitude, currentPage);
        String viewName = "/WEB-INF/rarefield/views/info/info_institution.jsp";
        modelAndView.setViewName(viewName);
        modelAndView.addObject("result", result);
        return modelAndView;
    }

    @RequestMapping(value = "/info_academicinfo", method = RequestMethod.GET)
    public ModelAndView rareDiseaseAcademicinfo(ModelAndView modelAndView){
        String viewName = "/WEB-INF/rarefield/views/info/info_academicinfo.jsp";

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

}
