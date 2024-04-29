<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header>
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
                <li class="nav-item"><a href="/user/user_login" class="nav-link fw-bold text-secondary"
                        aria-current="page">로그인/로그아웃</a>
                </li>
                <li class="nav-item"><a href="/user/user_mypage" class="nav-link text-secondary fw-bold">마이페이지</a>
                </li>

                <div class="dropdown-start dropstart">
                    <a class="btn  dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <svg width="30" height="20" viewBox="0 0 30 20" fill="none"
                            xmlns="http://www.w3.org/2000/svg">
                            <path d="M0 1H30" stroke="#CCCCCC" />
                            <path d="M0 10H30" stroke="#CCCCCC" />
                            <path d="M0 19H30" stroke="#CCCCCC" />
                        </svg>

                    </a>
                    <div class="aside in dropdown-menu container" aria-labelledby="dropdownMenuLink"
                        id="m_menu_aside" style="width: 1920px;">
                        <nav id="m_nav" role="navigation" class="row justify-content-center">
                            <ul class="th1 col-2 text-decoration-none">
                                <div id="field">최신마당</div>
                                <li class="menu_li dropdown-item ">
                                    <a href="/trend/trend_news" target="_self" title="" width="100px">
                                        뉴스</a>
                                </li>
                                <li class=" th2_in dropdown-item">

                                    <a href="/trend/trend_law" target="_self" title="">
                                        법, 시행령, 시행규칙</a>
                                </li>
                                <li class=" th2_in dropdown-item">

                                    <a href="/trend/trend_guideline" target="_self" title="">
                                        고시, 지침</a>
                                </li>


                                <!-- <li class=" th2_in dropdown-item">

                                    <a href="/trend/trend_document" target="_self" title="">
                                        민원서식</a>
                                </li> -->

                                <li class=" th2_in dropdown-item">

                                    <a href="/trend/trend_site" target="_self" title="">
                                        관련사이트</a>
                                </li>
                            </ul>
                            <ul class="th1 col-2">
                                <div id="field">정보마당</div>
                                <li class="menu_li dropdown-item">
                                    <a href="/info_raredisease" target="_self" title="">
                                        희귀질환정보검색</a>
                                </li>
                                <li class=" th2_in dropdown-item">

                                    <a href="/info_institution" target="_self" title="">
                                        의료기관검색</a>
                                </li>
                                <li class=" th2_in dropdown-item">

                                    <a href="/info_academicinfo" target="_self" title="">
                                        학술정보</a>
                                </li>
                            </ul>
                            <ul class="th1 col-2">
                                <div id="field">참여마당</div>

                                <li class="menu_li dropdown-item">
                                    <a href="/empo/empo_community" target="_self" title="">
                                        커뮤니티</a>
                                </li>
                                <li class=" th2_in dropdown-item">

                                    <a href="/empo/empo_program" target="_self" title="">
                                        프로그램</a>
                                </li>
                            </ul>
                            <ul class="th1 col-2">
                                <div id="field">공지마당</div>

                                <li class="menu_li dropdown-item">
                                    <a href="/other/other_notice" target="_self" title="">
                                        공지사항</a>
                                </li>
                                <li class=" th2_in dropdown-item">

                                    <a href="/other/other_QnA_main" target="_self" title="">
                                        QnA</a>
                                </li>
                            </ul>
                            <ul class="th1 col-2">
                                <a href="/manag/managmain" class="text-decoration-none" id="field">관리자마당</a>
                                <li class="menu_li dropdown-item">
                                    <a href="/manag/manag_user_main" target="_self" title="">
                                        회원관리</a>
                                </li>
                                <li class=" th2_in dropdown-item">
                                    <a href="/manag/manag_community_main" target="_self" title="">
                                        커뮤니티관리</a>
                                </li>
                                <li class=" th2_in dropdown-item">
                                    <a href="/manag/manag_program_main" target="_self" title="">
                                        프로그램서비스관리</a>
                                </li>
                                <li class=" th2_in dropdown-item">
                                    <a href="/manag/manag_QnA_main" target="_self" title="">
                                        QnA관리</a>
                                </li>
                                <li class=" th2_in dropdown-item">
                                    <a href="/manag/manag_notice_main" target="_self" title="">
                                        공지사항관리</a>
                                </li>
                                <li class=" th2_in dropdown-item">
                                    <a href="/manag/data_analytics" target="_self" title="">
                                        Data Analytics</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </ul>
        </div>
    </div>
</header>