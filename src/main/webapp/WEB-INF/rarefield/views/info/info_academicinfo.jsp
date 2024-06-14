<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/rarefield/views/commons/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <div class="side-banner-left">
        <%@ include file="/WEB-INF/rarefield/views/commons/side_left_banner.jsp" %>
    </div>

        <div class="col-8 row">
            <div class="container">
                <h2>news</h2>
                <div class="justify-content-center row">
                    <div class="row col-7">
                        <div class="col-3">
                            <select class="form-control" name="key_name">
                                <option value="thesis_name" {{ 'selected' if
                                    request._query_params.key_name=='thesis_name' else '' }}>
                                    제목
                                </option>
                                <option value="thesis_date" {{ 'selected' if
                                    request._query_params.key_name=='thesis_date' else '' }}>
                                    발행연도
                                </option>
                            </select>
                        </div>
                        <div class="col-6">
                            <input class="form-control" placeholder="Enter Search!" name="search_word"
                                value="{{request._query_params.word}}">
                        </div>
                        <div class="col-2">
                            <button type="submit" class="btn btn-primary" formaction="/info/info_academicinfo"
                                formmethod="get">검색</button>
                        </div>
                    </div>
                </div>

                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>등록일자</th>
                            <th>분류</th>
                        </tr>
                    </thead>
                    <tbody>
                        {% for paper in papers %}
                        <tr onclick="location.href='/info/info_academicinfo/{{paper.id}}'"
                            style="cursor: pointer;">
                            <td>{{pagination.start_record_number + loop.index0}}</td>
                            <td id="readpage">{{paper.ques_title}}</td>
                            <td>{{paper.date}}</td>
                            <td>{{paper.content}}</td>
                        </tr>
                        {% endfor %}
                    </tbody>
                </table>
                <!-- <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item {{ '' if pagination.has_previous_block else 'disabled' }}">
                <button  style="border: none; background: none;" type="submit" class="page-link" formaction="/info/info_academicinfo/{{pagination.first_page}}">
                    <svg width="21" height="18" viewBox="0 0 21 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M18 3L12 9L18 15" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                        <path d="M10 3L4 9L10 15" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                    </svg>
                </button>
            </li>
            <li class="page-item {{ '' if pagination.has_previous_page else 'disabled' }}">
                <button  style="border: none; background: none;" type="submit" class="page-link"
                    formaction="/info/info_academicinfo/{{pagination.previous_page}}"><svg width="13" height="18" viewBox="0 0 13 18" fill="none"
                    xmlns="http://www.w3.org/2000/svg">
                    <path d="M10 3L4 9L10 15" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                </svg></button>
            </li>
            {% for page_num in pagination.current_page_range %}
            <li class=" page-item {{ 'active' if page_num == pagination.current_page else '' }}" id="pages">
                <button  style="border: none; background: none; color: black;" type=" submit" class="page-link" formaction="/info/info_academicinfo/{{ page_num }}">
                    {{page_num}}
                </button>
            </li>
            {% endfor %}

            <li class=" page-item {{ '' if pagination.has_next_page else 'disabled' }}">
                <button  style="border: none; background: none;" type=" submit" class="page-link"
                    formaction="/info/info_academicinfo/{{ pagination.next_page }}"><svg width="13" height="18" viewBox="0 0 13 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M3 15L9 9L3 3" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                    </svg>
                <path d="M4 0V22" stroke="#EDEDED" stroke-width="7" />
            </svg></button>
            </li>
            <li class=" page-item {{ '' if pagination.has_next_block else ' disabled' }}">
                <button  style="border: none; background: none;" type=" submit" class="page-link" formaction="/info/info_academicinfo/{{ pagination.last_page }}">
                    <svg width="21" height="18" viewBox="0 0 21 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M3 15L9 9L3 3" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                        <path d="M11 15L17 9L11 3" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                    </svg>
                </button>
            </li>
        </ul>
    </nav> -->
            </div>
        </div>
        <div class="side-banner-right">
        <%@ include file="/WEB-INF/rarefield/views/commons/side_right_banner.jsp" %>
    </div>

</main>
        <hr>

        <%@ include file="/WEB-INF/rarefield/views/commons/footer.jsp" %>


            <script>



            </script>

