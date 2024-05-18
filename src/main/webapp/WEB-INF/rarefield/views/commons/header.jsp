<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>희귀질환정보 공유 플랫폼 Rare Field</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="/data/img/favicon.ico">
    <style>
        #maps >*{
            border-style: dotted 1px black;
        }
        * {
            
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

        a {
            text-decoration: none;
            color: black;
            text-decoration-line: none;
        }

        /* #float_banner1 {
            position: fixed;

            overflow: hidden;
        }
        #float_banner2 {
            position: fixed;
            overflow: hidden;
        } */
    </style>
</head>

<header>
    <sec:authentication property="principal" var="userDetailsBean" />
    <div class="container-fluid">
        <div
            class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom navbar navbar-expand-lg bd-navbar sticky-top justify-content-center">

            <a href="/"
                class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
                <svg class="bi me-2" width="40" height="32">
                    <use xlink:href="#bootstrap"></use>
                </svg>
                <span class="fs-4"><img width="200" src="/data/img/RDS_logo.png" alt=""></span>
            </a>

            <ul class="nav nav-pills">
            
                <%-- 로그인이 안된상태 --%>
                <sec:authorize access="isAnonymous()">
           
                    <li class="nav-item"><a href="/user_login" class="nav-link fw-bold text-secondary"
                        aria-current="page">로그인</a>
                    </li>
                </sec:authorize>
                
                <%-- 로그인이 된 상태 --%>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-link">
                        ID : ${userDetailsBean.username}
                    </li>
                    <li class="nav-item"><a href="/user_mypage" class="nav-link text-secondary fw-bold">마이페이지</a>
                    </li>
                    <li class="nav-item"><a href="/logout" class="nav-link fw-bold text-secondary"
                        aria-current="page">로그아웃</a>
                    </li>
                </sec:authorize>

            
                <div class="dropdown-start dropstart">
                        <div class="btn  dropdown-toggle" role="button" id="dropdownMenuLink"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            <svg width="30" height="20" viewBox="0 0 30 20" fill="none"
                                xmlns="http://www.w3.org/2000/svg">
                                <path d="M0 1H30" stroke="#CCCCCC" />
                                <path d="M0 10H30" stroke="#CCCCCC" />
                                <path d="M0 19H30" stroke="#CCCCCC" />
                            </svg>

                        </div>
                        <div class="aside in dropdown-menu container" aria-labelledby="dropdownMenuLink"
                            id="m_menu_aside" style="width: 1920px;">
                            <nav id="m_nav" role="navigation" class="row justify-content-center">
                                <ul class="th1 col-2 text-decoration-none">
                                    <div id="field">최신마당</div>
                                    <li class="menu_li dropdown-item ">
                                        <a href="/trend/news" target="_self" title="" width="100px">
                                            뉴스</a>
                                    </li>
                                    <li class=" th2_in dropdown-item">

                                        <a href="/trend/law" target="_self" title="">
                                            법, 시행령, 시행규칙</a>
                                    </li>
                                    <li class=" th2_in dropdown-item">

                                        <a href="/trend/trend_guideline" target="_self" title="">
                                            고시, 지침</a>
                                    </li> 

                                    <li class=" th2_in dropdown-item">

                                        <a href="/trend/trend_site" target="_self" title="">
                                            관련사이트</a>
                                    </li>
                                </ul>
                                <ul class="th1 col-2">
                                    <div id="field">정보마당</div>
                                    <li class="menu_li dropdown-item">
                                        <a href="/info/info_raredisease" target="_self" title="">
                                            희귀질환정보검색</a>
                                    </li>
                                    <li class=" th2_in dropdown-item">

                                        <a href="/info/info_institution" target="_self" title="">
                                            의료기관검색</a>
                                    </li>
                                    <li class=" th2_in dropdown-item">

                                        <a href="/info/info_academicinfo" target="_self" title="">
                                            학술정보</a>
                                    </li>
                                </ul>
                                <ul class="th1 col-2">
                                    <div id="field">참여마당</div>

                                    <li class="menu_li dropdown-item">
                                        <a href="/empo_community/selectSearch" target="_self" title="">
                                            커뮤니티</a>
                                    </li>
                                    <li class=" th2_in dropdown-item">

                                        <a href="/empo_program" target="_self" title="">
                                            프로그램</a>
                                    </li>
                                </ul>
                                <ul class="th1 col-2">
                                    <div id="field">공지마당</div>

                                    <li class="menu_li dropdown-item">
                                        <a href="/other_notice" target="_self" title="">
                                            공지사항</a>
                                    </li>
                                    <li class=" th2_in dropdown-item">

                                        <a href="/other_QnA_main" target="_self" title="">
                                            QnA</a>
                                    </li>
                                </ul>
                            
                            </nav>
                        </div>
                </div>

            </ul>
        </div>
    </div>
</header>
