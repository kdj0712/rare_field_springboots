package com.yojulab.study_springboot.service.rarefield.empocommunity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yojulab.study_springboot.dao.RareSharedDao;

@Service
@Transactional
public class EmpoCommunityService {

    @Autowired
    RareSharedDao RaresharedDao;

    public Object insert(Map dataMap) {
        String sqlMapId = "RarefieldEmpocommunity.insert";
        Object result = RaresharedDao.insert(sqlMapId, dataMap);
        return result;
    }
}
