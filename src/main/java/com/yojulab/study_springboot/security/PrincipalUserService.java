package com.yojulab.study_springboot.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.yojulab.study_springboot.service.rarefield.users.UserService;
import com.yojulab.study_springboot.service.sample.UsersService;

@Service
public class PrincipalUserService implements UserDetailsService {

    @Autowired
    UserService userService;


    @Override
    // url /login 일때 spring scrutiry가 호출
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // query select with ID
        Map dataMap = new HashMap<>();
        dataMap.put("user_ID", username);
        Object usernameObj = username;
        Map resultMap = (Map) userService.selectByUIDWithAuths(dataMap);

        // session 등록
        PrincipalUser principalUser = new PrincipalUser(resultMap);

        return principalUser;
    }
    
}