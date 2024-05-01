package com.yojulab.study_springboot.service.rarefield.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yojulab.study_springboot.dao.RareSharedDao;
import com.yojulab.study_springboot.service.sample.AuthsService;
import com.yojulab.study_springboot.utils.Commons;


@Service
@Transactional
public class UserService {

    @Autowired
    RareSharedDao rareSharedDao;

    @Autowired
    Commons commonUtils;

    @Autowired
    AuthsService AUTHSService;
    
    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder;

    // user에 대한 insert, 
    public Object insert(Map dataMap){
        String password = (String)dataMap.get("user_pswd");
        dataMap.put("user_pswd",bcryptPasswordEncoder.encode(password));

        String sqlMapId = "RarefieldUsers.insert";
        Object result = rareSharedDao.insert(sqlMapId, dataMap);
        return result;
    }

    // user에 대한 insert 및 auth 부분
    public Object insertWithAuth(Map dataMap){
        Object result = this.insert(dataMap);
        result = AUTHSService.insert(dataMap);
        return result;
    }
}
