package com.yojulab.study_springboot.controller.rarefield.rarediseaseinfo;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.yojulab.study_springboot.service.rarefield.rest.RestTemplateService;
@Controller
@RequestMapping("/info")
public class RarediseaseInfo {
    @Autowired
    RestTemplateService restTemplateService;
    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;
    @RequestMapping(value = "/info_institution", method = { RequestMethod.GET, RequestMethod.POST })
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
    @RequestMapping(value = "/info_academicinfo", method = RequestMethod.GET)
    public ModelAndView rareDiseaseAcademicinfo(ModelAndView modelAndView) {
        String viewName = "/WEB-INF/rarefield/views/info/info_academicinfo.jsp";
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}