package com.yojulab.study_springboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Value;

@Controller
public class MainController {
    @Value("${remote.server.url}")
    private String remoteServerUrl;

    @GetMapping({ "/", "/home", "/main" })
    public ModelAndView main(ModelAndView modelAndView) {
        // 현재 사용자 Authentication 객체 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자가 인증되었는지 확인
        if (authentication.isAuthenticated()) {
            // Principal 객체에서 UserDetails 인터페이스를 구현한 사용자 정보 가져오기
            Object principal = authentication.getPrincipal();
            UserDetails userDetails = (principal instanceof UserDetails) ? (UserDetails) principal : null;

            if (userDetails != null) {
                // 사용자 이름 가져오기
                String username = userDetails.getUsername();
                // authorities에서 권한 정보 가져오기
                // StringBuilder authorities = new StringBuilder();
                // for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                // authorities.append(grantedAuthority.getAuthority()).append(", ");
                // }
                String authorities = authentication.getAuthorities().toString();
            }
        }
        modelAndView.addObject("name", "Yojulab!");

        modelAndView.addObject("remoteServerUrl", remoteServerUrl);
        modelAndView.addObject("myimage", "thermometer.png");

        modelAndView.setViewName("/WEB-INF/views/main.jsp");
        return modelAndView;
    }

    @GetMapping({ "/admin" }) // 관리자 접속하는 곳
    public ModelAndView admin(ModelAndView modelAndView) {
        String viewName = "/WEB-INF/views/admin.jsp";
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @GetMapping({ "/manager/read" }) // 관리자 접속하는 곳
    public ModelAndView manager(ModelAndView modelAndView) {
        String viewName = "/WEB-INF/views/manager/read.jsp";
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
