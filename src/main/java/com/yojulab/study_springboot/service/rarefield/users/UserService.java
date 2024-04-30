package com.yojulab.study_springboot.service.rarefield.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yojulab.study_springboot.dao.SharedDao;
import com.yojulab.study_springboot.service.sample.AuthsService;
import com.yojulab.study_springboot.utils.Commons;


@Service
@Transactional
public class UserService {

    @Autowired
    SharedDao sharedDao;

    @Autowired
    Commons commonUtils;

    @Autowired
    AuthsService AUTHSService;

    // user에 대한 insert, 
    public Object insert(Map dataMap){
        return 0;
    }

    // user에 대한 insert 및 auth 부분
    public Object insertWithAuth(Map dataMap){
        Object result = this.insert(dataMap);
        return 0;
    }
}
