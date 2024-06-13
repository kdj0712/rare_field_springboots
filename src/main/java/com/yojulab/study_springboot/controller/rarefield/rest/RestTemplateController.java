// package com.yojulab.study_springboot.controller.rarefield.rest;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.yojulab.study_springboot.service.rarefield.rest.RestTemplateService;
// import org.springframework.web.bind.annotation.RequestBody;



// @RestController
// @RequestMapping("/api/rest-template")
// public class RestTemplateController {
//     RestTemplateService restTemplateService;

//   @Autowired
//   public RestTemplateController(RestTemplateService restTemplateService) {
//     this.restTemplateService = restTemplateService;
//   }

//   @GetMapping(value = "/around-hub")
//   public String getAroundHub() {
//     return restTemplateService.getAroundHub();
//   }

//   @PostMapping("path")
//   public String postMethodName(@RequestBody String entity) {
//       //TODO: process POST request
      
//       return entity;
//   }
  

//   @GetMapping(value = "/name")
//   public String getName() {
//     return restTemplateService.getName();
//   }

//   @GetMapping(value = "/name2")
//   public String getName2() {
//     return restTemplateService.getName2();
//   }

//   @PostMapping(value = "/dto")
//   public ResponseEntity<MemberDTO> postDto() {
//     return restTemplateService.postDto();
//   }

//   @PostMapping(value = "/add-header")
//   public ResponseEntity<MemberDTO> addHeader() {
//     return restTemplateService.addHeader();
//   }
// }

package com.yojulab.study_springboot.controller.rarefield.rest;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.yojulab.study_springboot.service.rarefield.rest.RestTemplateService;

import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;


@RestController
@RequestMapping("/trend")
public class RestTemplateController {
  
    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping(value = "/law")
      public ModelAndView law(ModelAndView modelAndView) {
        List<Map<String,Object>> result = restTemplateService.lawPostRequest();
        String viewPath = "trend/trend_law";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("result", result);
        return modelAndView;
      }

      @GetMapping(value = "/news")
        public ModelAndView news(ModelAndView modelAndView, @RequestParam int currentPage) {
        Map<String,Object> result = restTemplateService.newsPostRequest(currentPage);
        String viewPath = "trend/trend_news";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("result", result);
        return modelAndView;
      }

      @GetMapping(value = "/")
        public ModelAndView mainNews(ModelAndView modelAndView, @RequestParam int currentPage) {
        Map<String,Object> result = restTemplateService.newsPostRequest(currentPage);
        String viewPath = "mainpage";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("result", result);
        return modelAndView;
      }


      @GetMapping(value = "/read/{id}")
        public ModelAndView newsRead(ModelAndView modelAndView,@PathVariable String id, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage) {
        Map<String,Object> result = restTemplateService.newsReadGetRequest(id);
        // restTemplateService.newsReadPostRequest(result, "_id", id);
        String viewPath = "/trend/trend_news_read.jsp";
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
