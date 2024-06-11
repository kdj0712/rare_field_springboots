package com.yojulab.study_springboot.controller.rarefield.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yojulab.study_springboot.service.rarefield.rest.RestTemplateService;


@RestController
@RequestMapping("/trend")
public class RestTemplateController {
  
    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping(value = "/law")
      public ModelAndView law(ModelAndView modelAndView) {
        List<Map<String,Object>> result = restTemplateService.lawPostRequest();
        String viewPath = "/WEB-INF/rarefield/views/trend/trend_law.jsp";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("result", result);
        return modelAndView;
      }

      @GetMapping(value = "/news")
        public ModelAndView news(ModelAndView modelAndView, @RequestParam int currentPage) {
        Map<String,Object> result = restTemplateService.newsPostRequest(currentPage);
        String viewPath = "/WEB-INF/rarefield/views/trend/trend_news.jsp";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("result", result);
        return modelAndView;
      }

      @GetMapping(value = "/")
        public ModelAndView mainNews(ModelAndView modelAndView, @RequestParam int currentPage) {
        Map<String,Object> result = restTemplateService.newsPostRequest(currentPage);
        String viewPath = "/WEB-INF/rarefield/views/mainpage.jsp";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("result", result);
        return modelAndView;
      }


      @GetMapping(value = "/read/{id}")
        public ModelAndView newsRead(ModelAndView modelAndView,@PathVariable String id, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage) {
        Map<String,Object> result = restTemplateService.newsReadGetRequest(id);
        // restTemplateService.newsReadPostRequest(result, "_id", id);
        String viewPath = "/WEB-INF/rarefield/views/trend/trend_news_read.jsp";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("result", result);
        modelAndView.addObject("_id", id);
        modelAndView.addObject("currentPage", currentPage);
        return modelAndView;
      }

      // @GetMapping(value = "/guideline")
      // public ModelAndView guideline(ModelAndView modelAndView) {
      //   List<Map<String,Object>> result = restTemplateService.guideLine();
      //   String viewPath = "/WEB-INF/rarefield/views/trend/trend_guideline.jsp";
      //   modelAndView.setViewName(viewPath);
      //   modelAndView.addObject("result", result);
      //   return modelAndView;
      // }

      // @GetMapping(value = "/site")
      // public String site() {
      //  restTemplateService.newsPostRequest();
      // return "hellow";
      // }
  
}
