package com.yojulab.study_springboot.controller.rarefield.rest;

import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Whitelist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yojulab.study_springboot.service.rarefield.rest.RestTemplateService;

import jakarta.servlet.http.HttpSession;

@SuppressWarnings({ "deprecation", "unused" })
@RestController
@RequestMapping("/trend")
public class RestTemplateController {

  @Autowired
  RestTemplateService restTemplateService;

  @GetMapping(value = "/law")
  public ModelAndView law(ModelAndView modelAndView) {
    List<Map<String, Object>> result = restTemplateService.lawPostRequest();
    String viewPath = "trend/trend_law";
    modelAndView.setViewName(viewPath);
    modelAndView.addObject("result", result);
    return modelAndView;
  }

  @GetMapping(value = "/news")
  public ModelAndView news(ModelAndView modelAndView,
  @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {
    Map<String, Object> result = restTemplateService.newsPostRequest(currentPage);
    String viewPath = "trend/trend_news";
    modelAndView.setViewName(viewPath);
    modelAndView.addObject("result", result);
    modelAndView.addObject("currentPage", currentPage);
    return modelAndView;
  }

  // @GetMapping(value = "/")
  // public ModelAndView mainNews(ModelAndView modelAndView, @RequestParam int currentPage) {
  //   Map<String, Object> result = restTemplateService.newsPostRequest(currentPage);
  //   String viewPath = "mainpage";
  //   modelAndView.setViewName(viewPath);
  //   modelAndView.addObject("result", result);
  //   return modelAndView;
  // }

  @GetMapping(value = "/read/{id}")
  public ModelAndView newsRead(ModelAndView modelAndView, 
                               @PathVariable String id,
                               HttpSession session) throws Exception {
  
      Integer currentPage = (Integer) session.getAttribute("currentPage");
      if (currentPage == null) {
          currentPage = 1; // 기본값 설정
      }
  
      Map<String, Object> result = restTemplateService.newsReadGetRequest(id);
  
      String newsContents = (String) result.get("news_contents");
      String updatedContents = addSpacingToParagraphs(newsContents);
  
      String viewPath = "trend/trend_news_read";
      modelAndView.setViewName(viewPath);
      modelAndView.addObject("result", result);
      modelAndView.addObject("id", id);
      modelAndView.addObject("currentPage", currentPage);
      modelAndView.addObject("updatedContents", updatedContents);
      return modelAndView;
  }

  private String addSpacingToParagraphs(String contents) {
    // Jsoup을 사용하여 HTML을 파싱합니다.
    Document doc = Jsoup.parse(contents, "", Parser.htmlParser());

    // <p> 태그를 줄바꿈 문자로 대체합니다.
    for (Element element : doc.select("p")) {
      element.before("\n");
      element.after("\n");
    }

    // 변환된 HTML 내용을 문자열로 반환합니다.
    String updatedContents = doc.body().html();
    return updatedContents;
  }
  // @GetMapping(value = "/guideline")
  // public ModelAndView guideline(ModelAndView modelAndView) {
  // List<Map<String,Object>> result = restTemplateService.guideLine();
  // String viewPath = "/WEB-INF/rarefield/views/trend/trend_guideline.jsp";
  // modelAndView.setViewName(viewPath);
  // modelAndView.addObject("result", result);
  // return modelAndView;
  // }

  // @GetMapping(value = "/site")
  // public String site() {
  // restTemplateService.newsPostRequest();
  // return "hellow";
  // }

}
