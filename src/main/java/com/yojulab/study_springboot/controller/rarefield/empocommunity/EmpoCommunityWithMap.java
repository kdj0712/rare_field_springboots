package com.yojulab.study_springboot.controller.rarefield.empocommunity;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yojulab.study_springboot.service.rarefield.empocommunity.EmpoCommunityService;


@Controller
@RequestMapping("/carInfor/map")
public class EmpoCommunityWithMap {
    // pagination controller 만들기, select, delete, update

    @Autowired
    EmpoCommunityService empoCommunityService;

    // /selectSearch?search=YEAR&words=2020
    // /selectSearch/CAR_NAME/소
    // @GetMapping("/selectSearch")
    // public ModelAndView selectSearch(@RequestParam Map params
    //                         , ModelAndView modelAndView) {
    //     // Object result = carInforsService.selectSearch(params);
    //     Object result = empoCommunityService.selectSearchWithPagination(params);
    //     modelAndView.addObject("params", params);
    //     modelAndView.addObject("result", result);
        
    //     modelAndView.setViewName("/WEB-INF/views/carinfor/list_map.jsp");
    //     return modelAndView;
    // }

    // // delete with MVC
    // @PostMapping("/deleteAndSelectSearch/{UNIQUE_ID}")
    // public ModelAndView deleteAndSelectSearch(@PathVariable String UNIQUE_ID
    //                     , @RequestParam Map params, ModelAndView modelAndView) {
    //     Object result = empoCommunityService.deleteAndSelectSearch(UNIQUE_ID, params);
    //     modelAndView.addObject("params", params);
    //     modelAndView.addObject("result", result);

    //     modelAndView.setViewName("/WEB-INF/views/carinfor/list_map.jsp");
    //     return modelAndView;
    // }

    // // /selectDetail/CI002
    // @GetMapping("/selectDetail/{UNIQUE_ID}")
    // public ModelAndView selectDetail(@PathVariable String UNIQUE_ID
    //                     , @RequestParam Map params, ModelAndView modelAndView) {
    //     Object result = empoCommunityService.selectDetail(UNIQUE_ID, params);
    //     modelAndView.addObject("params", params);

    //     modelAndView.setViewName("/WEB-INF/views/carinfor/list_map.jsp");
    //     return modelAndView;
    // }
}
