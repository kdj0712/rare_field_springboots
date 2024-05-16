<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap, java.util.List, com.yojulab.study_springboot.utils.Paginations" %>
<%@ page import="java.util.Map" %>
<%@ include file="/WEB-INF/rarefield/views/commons/header.jsp" %>
<style>
    * {
        border-style: dotted 1px black;
        font-family: "Noto Sans KR", sans-serif;
        font-optical-sizing: auto;
        list-style-type: none;
        text-decoration: none;
        color: black;
        text-decoration-line: none;


    }

    #field {
        font-size: larger;
        font-weight: bold;
    }

    a,
    li * {
        text-decoration: none;
        color: black;
        text-decoration-line: none;


        ;
    }
</style>
<main class="row justify-content-between">
    
<div class="col-2"><%@ include file="/WEB-INF/rarefield/views/commons/side_left_banner.jsp" %></div>

    <div class="container col-10" style="width: 80%">
        <h4 class="text-center m-3 fw-bold" width="100%" height="100px"> <a href="/trend/law">법/시행령/시행규칙</a> </h4>
        <hr>
        <% List<Map<String,Object>> resultList = (List<Map<String,Object>>) request.getAttribute("result");  
           for(int i = 0 ; i < resultList.size() ; i=i+1) {
            HashMap<String, Object> record = (HashMap<String, Object>) resultList.get(i); %>
        <div class="container" style="width: 80%;" onclick="location.href='<%= record.get("link") %>'">
            <div class="tit">
                <h5 class="">

                    <a href='<%= record.get("link") %>' class="">
                        <%= record.get("law_name") %>

                    </a>
                </h5>
            </div>
            <div class="sub row justify-content-between">
                <h7 class="name col-3">
                    
                    <a href='<%= record.get("link") %>' class="">
                        공포번호 : <%= record.get("promulgation_number") %>
                       
                    </a>
                </h7>
                <h7 class="name col-3">
                   
                    <a href='<%= record.get("link") %>' class="">
                        공포일자 : <%= record.get("promulgation_date") %>
                       
                    </a>
                </h7>
                <h7 class="name col-3">
                   
                    <a href='<%= record.get("link") %>' class="">
                        시행일자 : <%= record.get("start_date") %>
                    
                    </a>
                </h7>
            </div>
        </div>
        <% } %>
        <hr>
        
    </div>
    <div class="col-1"><%@ include file="/WEB-INF/rarefield/views/commons/side_right_banner.jsp" %></div>
    
</main>
<%@ include file="/WEB-INF/rarefield/views/commons/footer.jsp" %>