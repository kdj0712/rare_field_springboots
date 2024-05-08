package com.yojulab.study_springboot.controller.rarefield.empocommunity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yojulab.study_springboot.service.rarefield.empocommunity.EmpoCommunityService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/empo_community")
public class EmpoCommunityData {

    // @Autowired
    // EmpoCommunityService empoCommunity;

    // @PostMapping("/insert")
    // public ResponseEntity insert(@RequestParam Map paramMap) {
    //     Object result = empoCommunity.insert(paramMap);
    //     return ResponseEntity.ok().body(result);
    // }


}
