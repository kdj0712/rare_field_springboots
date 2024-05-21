<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap, java.util.List, com.yojulab.study_springboot.utils.Paginations" %>
<%@ page import="java.util.Map" %>
<%@ include file="/WEB-INF/rarefield/views/commons/header.jsp" %>
<!-- Quill 편집기의 스타일 지정 -->
<style>
    #editor {
        height: 35rem;
        overflow: auto;
    }
</style>


<main class="row justify-content-between">
    <%@ include file="/WEB-INF/rarefield/views/commons/side_left_banner.jsp" %>
    <% int currentPage = (Integer) request.getAttribute("currentPage"); %>
    <div class="col-8 row">
    <% Map<String,Object> result = (Map<String,Object>) request.getAttribute("result"); %>

    <div class="mt-4 mb-4">
        <h2 style="margin-top: 100px;"><%= result.get("news_title") %></h2>
    </div>
    <div class="row">
        <div class="col-12 text-end">
            언론사명 : <%= result.get("news_paper") %>
        </div>
        <div class="col-12 text-end">
            날짜 : <%= result.get("news_datetime") %>
        </div>
    </div>
    <hr>
    <div style="">
        <img src="<%= result.get("news_image") %>" alt="">
    </div>
    <div class="lead" style="text-align:justify; font-size: 18px;" id="editor"></div>
    <hr>
    <div>뉴스 링크 :
        <a href="<%= result.get("news_url") %>" target="_blank"><%= result.get("news_url") %></a>
    </div>
    <hr>
    <div class="row">
        <div class="col-12 text-end">

            <a href="/trend/news?currentPage=<%= currentPage %>" class="btn btn-primary">목록</a>

        </div>
    </div>
</div>
    <%@ include file="/WEB-INF/rarefield/views/commons/side_right_banner.jsp" %>
</main>

<%@ include file="/WEB-INF/rarefield/views/commons/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/quill@2.0.0-beta.0/dist/quill.js"></script>

<script>
    // 서버나 다른 소스에서 로드한 콘텐츠를 대표하는 가상의 데이터
    let contentFromServer = '<%= result.get("news_contents") %>';

    // Quill 편집기 초기화
    let quill = new Quill('#editor', {
        theme: 'snow',
        readOnly: true, // 읽기 전용으로 설정
        modules: {
            toolbar: false // 툴바 비활성화
        }
    });

    // 서버에서 가져온 콘텐츠로 편집기를 채움
    quill.root.innerHTML = contentFromServer;
</script>
