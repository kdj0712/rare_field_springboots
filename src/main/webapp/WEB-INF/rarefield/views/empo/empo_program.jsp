<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/rarefield/views/commons/header.jsp" %>

<style>
    /* 왼쪽 배너 스타일 */
    .side-banner-left {
        position: fixed;
        top: 0;
        left: 0;
        width: 16.66667%;
        /* 2/12의 너비 */
        height: 100%;
        /* 배너가 화면 전체 높이를 차지하도록 */
        overflow: auto;
        /* 배너 내용이 길어지면 스크롤 가능하게 */
        z-index: 1000;
        /* 다른 요소들 위에 배너가 위치하도록 */
    }

    /* 오른쪽 배너 스타일 */
    .side-banner-right {
        position: fixed;
        top: 0;
        right: 0;
        width: 16.66667%;
        /* 2/12의 너비 */
        height: 100%;
        /* 배너가 화면 전체 높이를 차지하도록 */
        overflow: auto;
        /* 배너 내용이 길어지면 스크롤 가능하게 */
        z-index: 1000;
        /* 다른 요소들 위에 배너가 위치하도록 */
    }

    /* 메인 컨텐츠 스타일 조정 */
    .main-content {
        margin-left: 16.66667%;
        /* 왼쪽 배너의 너비만큼 여백 추가 */
        margin-right: 16.66667%;
        /* 오른쪽 배너의 너비만큼 여백 추가 */
    }
</style>

<main class="row justify-content-between">

    <div class="side-banner-left col-2">
        <%@ include file="/WEB-INF/rarefield/views/commons/side_left_banner.jsp" %>
    </div>

    <div class="main-content col-8 row">
        <div class="container">
            <div class="row py-lg-5">
                <div class="col-md-8 ">
                    <div class="text-center" style="position: relative;">
                        <img src="${remoteServerUrl}/data/img/RF_programmbanner.jpg" alt="">
                        <a href="/rarefield" class="btn btn-outline-primary" style="position: absolute; top: 55%; left: 75%; transform: translate(-50%, -50%); opacity: 0.8;">
                            Rare Field 소개
                        </a>
                    </div>
                </div>
            </div>
            <div class="album py-5">
                <div class="container">
                    <div class="row row-cols-md-3">
                        <div class="card col">
                            <img src="${remoteServerUrl}/data/img/program_thumnail.jpg" class="card-img-top" alt="썸네일 이미지">
                            <div class="card-body">
                                <p class="card-text">상품설명 어쩌구 저쩌구 아ㅓ맬야ㅓㅁ야ㅐ러매.</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary">
                                            Apply
                                        </button>
                                    </div>
                                    <small class="text-body-secondary">월 84,800원</small>
                                </div>
                            </div>
                        </div>
                        <div class="card col">
                            <img src="${remoteServerUrl}/data/img/program_thumnail.jpg" class="card-img-top" alt="썸네일 이미지">
                            <div class="card-body">
                                <p class="card-text">상품설명 어쩌구 저쩌구 아ㅓ맬야ㅓㅁ야ㅐ러매.</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary">
                                            Apply
                                        </button>
                                    </div>
                                    <small class="text-body-secondary">월 84,800원</small>
                                </div>
                            </div>
                        </div>
                        <div class="card col">
                            <img src="${remoteServerUrl}/data/img/program_thumnail.jpg" class="card-img-top" alt="썸네일 이미지">
                            <div class="card-body">
                                <p class="card-text">상품설명 어쩌구 저쩌구 아ㅓ맬야ㅓㅁ야ㅐ러매.</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary">
                                            Apply
                                        </button>
                                    </div>
                                    <small class="text-body-secondary">월 84,800원</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="side-banner-right col-2">
        <%@ include file="/WEB-INF/rarefield/views/commons/side_right_banner.jsp" %>
    </div>

</main>
<hr>
<%@ include file="/WEB-INF/rarefield/views/commons/footer.jsp" %>

<script src="/docs/5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>