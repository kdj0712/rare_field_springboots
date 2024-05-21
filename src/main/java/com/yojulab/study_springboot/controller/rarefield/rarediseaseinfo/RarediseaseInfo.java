package com.yojulab.study_springboot.controller.rarefield.rarediseaseinfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.google.gson.Gson;
import com.yojulab.study_springboot.utils.PaginationUtils;
import com.yojulab.study_springboot.utils.Paginations;
import com.yojulab.study_springboot.service.rarefield.rest.RestTemplateService;

@RestController
@RequestMapping("/info")
public class RarediseaseInfo {
    
    @Autowired
    RestTemplateService restTemplateService;

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;


    // RequestMethod.GET
    @GetMapping("/info_institution")
    public ModelAndView institutionSearch(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String pos,
            @RequestParam(required = false) Integer currentPage,
            ModelAndView modelAndView) throws JsonProcessingException {
        Double latitude = null;
        Double longitude = null;
        if (pos != null && !pos.isEmpty()) {
            String[] parts = pos.split(",");
            if (parts.length == 2) {
                try {
                    latitude = Double.valueOf(parts[0]);
                    longitude = Double.valueOf(parts[1]);
                } catch (NumberFormatException e) {
                    modelAndView.addObject("message", "위치 정보가 잘못되었습니다.");
                    modelAndView.setViewName("/WEB-INF/rarefield/views/info/info_institution.jsp");
                    modelAndView.addObject("page");
                    return modelAndView;
                }
            }
        }
        String viewName = "/WEB-INF/rarefield/views/info/info_institution.jsp";
        modelAndView.setViewName(viewName);
        modelAndView.addObject("page");
        modelAndView.addObject("API_KEY", googleMapsApiKey);
        // if (keyword != null || (latitude != null && longitude != null)) {
        //     Map<String, Object> result = restTemplateService.institutionQueryRequest(
        //             keyword,
        //             latitude,
        //             longitude,
        //             currentPage != null ? currentPage : 1);
        //     if (result != null) {
        //         Gson gson = new Gson();
        //         String resultsJson = gson.toJson(result.get("results"));
        //         modelAndView.addObject("resultsFromPython", resultsJson);
        //         modelAndView.addObject("result", result);
        //     } else {
        //         modelAndView.addObject("message", "검색 결과가 없습니다.");
        //     }
        // } else {
        //     modelAndView.addObject("message", "검색 조건을 입력해주세요.");
        // }
        return modelAndView;
    }

    // @GetMapping(value = "/info_raredisease")
    // public ModelAndView dise_search(
    //         @RequestParam(required = false) String key_name,
    //         @RequestParam(required = false) String search_word,
    //         @RequestParam(required = false) Integer currentPage,
    //         ModelAndView modelAndView) {

    //     Integer page = (currentPage != null) ? currentPage : 1;

    //     Map<String, Object> result = null;
    //     try {
    //         result = restTemplateService.dise_search(page, key_name, search_word);
    //     } catch (JsonProcessingException e) {
    //         // 예외 처리 로직 추가
    //         e.printStackTrace();
    //         modelAndView.addObject("error", "데이터 처리 중 오류가 발생했습니다.");
    //     }

    //     String viewPath = "/WEB-INF/rarefield/views/info/info_raredisease.jsp";
    //     modelAndView.setViewName(viewPath);
    //     modelAndView.addObject("result", result);
    //     modelAndView.addObject("pagination", new Pagination(page));
    //     return modelAndView;
    // }

    @GetMapping(value = "/info_raredisease")
    public ModelAndView dise_search(
            @RequestParam(required = false) String key_name,
            @RequestParam(required = false) String search_word,
            @RequestParam(required = false) Integer currentPage,
            ModelAndView modelAndView) {

        Integer page = (currentPage != null) ? currentPage : 1;
        Map<String, Object> result = null;

        try {
            result = restTemplateService.dise_search(page, key_name, search_word);
        } catch (JsonProcessingException e) {
            // 예외 처리 로직 추가
            e.printStackTrace();
            modelAndView.addObject("error", "데이터 처리 중 오류가 발생했습니다.");
            modelAndView.setViewName("/WEB-INF/rarefield/views/error.jsp");
            return modelAndView;
        }
        List<Map<String, Object>> results = (List<Map<String, Object>>) result.get("results");
        // totalItems는 결과 맵에서 가져와야 합니다.
        int totalItems = 0; // 기본값 설정
        if (result != null && result.containsKey("pagination")) {
            Map<String, Object> pagination = (Map<String, Object>) result.get("pagination");
            if (pagination != null && pagination.containsKey("total_records")) {
                Object totalRecordsObj = pagination.get("total_records");
                if (totalRecordsObj instanceof Number) {
                    totalItems = ((Number) totalRecordsObj).intValue();
                }
            }
        }
        
        Paginations Paginations = new Paginations(totalItems,page);

        String viewPath = "/WEB-INF/rarefield/views/info/info_raredisease.jsp";
        modelAndView.setViewName(viewPath);
        modelAndView.addObject("resultList", results);
        modelAndView.addObject("paginations", Paginations);
        
        // // dataMap이 null인 경우 빈 맵을 설정
        // if (result == null) {
        //     result = new HashMap<>();
        // }
        // modelAndView.addObject("dataMap", result);

        return modelAndView;
    }
}