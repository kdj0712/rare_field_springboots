package com.yojulab.study_springboot.service.rarefield.empocommunity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yojulab.study_springboot.dao.RareSharedDao;
import com.yojulab.study_springboot.utils.Commons;

@Service
@Transactional
public class EmpoCommunityService {

    @Autowired
    RareSharedDao RaresharedDao;

    @Autowired
    Commons commons;

    public Object insert(Map dataMap) {
        
        // 1. community_ID 번호를 생성 + insert 쿼리문 불러오고 대입
        String sqlMapId = "RarefieldEmpocommunity.insert";
        String community_ID = commons.getUniqueSequence();
        dataMap.put("community_ID",community_ID);
        
        // 2. user_ID 가져오기
        
        Object result = RaresharedDao.insert(sqlMapId, dataMap);
        return result;
    }
}
