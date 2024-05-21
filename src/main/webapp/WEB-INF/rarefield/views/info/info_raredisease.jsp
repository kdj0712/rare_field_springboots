<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ page import="java.util.HashMap, java.util.ArrayList, com.yojulab.study_springboot.utils.Paginations" %>
    <%@ page import="java.util.Map" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ include file="/WEB-INF/rarefield/views/commons/header.jsp" %>
      <style>
        .table-fixed td,
        .table-fixed th {
          white-space: nowrap;
          width: 100px;
          /* 또는 원하는 너비 */
          overflow: hidden;
          text-overflow: ellipsis;
        }

        .center-fixed {
          text-align: center;
          white-space: nowrap;
          width: 100px;
          /* 조정 가능한 너비 */
          overflow: hidden;
          text-overflow: ellipsis;
        }

        .text-center {
          text-align: center;
        }

        li.col-3 {
          padding: 5px;
          margin: 10px;
        }

        li.col-3:hover {
          background-color: deepskyblue;
        }

        .btn-4 {
          background-color: #4dccc6;
          background-image: linear-gradient(315deg, #4dccc6 0%, #96e4df 74%);
          line-height: 42px;
          padding-left: 10px;
          padding-right: 10px;
          border: none;
        }

        .btn-4:hover {
          background-color: #89d8d3;
          background-image: linear-gradient(315deg, #89d8d3 0%, #03c8a8 74%);
        }

        .btn-4 span {
          position: relative;
          display: block;
          width: 100%;
          height: 100%;
        }

        .btn-4:before,
        .btn-4:after {
          position: absolute;
          content: "";
          right: 0;
          top: 0;
          box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .9),
            -4px -4px 6px 0 rgba(116, 125, 136, .2),
            inset -4px -4px 6px 0 rgba(255, 255, 255, .9),
            inset 4px 4px 6px 0 rgba(116, 125, 136, .3);
          transition: all 0.3s ease;
        }

        .btn-4:before {
          height: 0%;
          width: .1px;
        }

        .btn-4:after {
          width: 0%;
          height: .1px;
        }

        .btn-4:hover:before {
          height: 100%;
        }

        .btn-4:hover:after {
          width: 100%;
        }

        .btn-4 span:before,
        .btn-4 span:after {
          position: absolute;
          content: "";
          left: 0;
          bottom: 0;
          box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .9),
            -4px -4px 6px 0 rgba(116, 125, 136, .2),
            inset -4px -4px 6px 0 rgba(255, 255, 255, .9),
            inset 4px 4px 6px 0 rgba(116, 125, 136, .3);
          transition: all 0.3s ease;
        }

        .btn-4 span:before {
          width: .1px;
          height: 0%;
        }

        .btn-4 span:after {
          width: 0%;
          height: .1px;
        }

        .btn-4 span:hover:before {
          height: 100%;
        }

        .btn-4 span:hover:after {
          width: 100%;
        }
      </style>


        <main class="row justify-content-between">
          <div class="col-2">
            <%@ include file="/WEB-INF/rarefield/views/commons/side_left_banner.jsp" %>
          </div>

          <div class="col-8 row">
            <div class="container">
              <form action="">
                <div class="row" style="justify-content: center;">
                  <div class="col-10.5 row text-center"
                    style="align-items: center;  border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); justify-content: center;">
                    <ul class="row col list-unstyled justify-content-center">

                      <li class="col-3 btn mt-4"
                        style=" height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); " onclick="fetchAllDiseases()">전체질환</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('A00', 'B99')">특정감염성(A00-B99)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('C00','D48')">신생물(C00-D48)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('D50','D89')">혈액조혈기관(D50-D89)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('E00','E90')">내분비,영양(E00-E90)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('F00','F99')">정신및행동(F00-F99)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('G00','G99')">신경계통질환(G00-G99)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('H00','H59')">눈및부속기관(H00-H59)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('I00','I99')">순환기계통(I00-I99)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('J00','J99')">호흡기계통(J00-J99)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('K00','K93')">소화기계통(K00-K93)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('L00','L99')">피부및조직(L00-L99)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('M00','M99')">근육골격계통(M00-M99)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('N00','N99')">비뇨생식(N00-N99)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('P00','P96')">출생전후기(P00-P96)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); "
                          onclick="fetchDataByRange('Q00','Q99')">선천기형변형(Q00-Q99)</a>
                      </li>
                      <li class="col-3 btn mt-4"
                        style="height: 20%; height: 20%; border-radius: 45px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); color: black;">
                        <a href="#" style="color: rgb(0, 0, 0); " onclick="fetchDataByRange('코드 없음')">코드 없음</a>
                      </li>
                      <li class="col-3 text-end" style="border: none; background: none;"
                        onclick="javascript:fn_opendiv();return false;">
                        <svg width="50" height="50" viewBox="0 0 61 56" fill="none" xmlns="http://www.w3.org/2000/svg">
                          <g filter="url(#filter0_d_0_1)">
                            <path
                              d="M5.38721 27.9452C5.38721 15.3223 15.6201 5.08936 28.243 5.08936H32.5479C45.1708 5.08936 55.4037 15.3223 55.4037 27.9452C55.4037 40.5681 45.1708 50.801 32.5479 50.801H28.2431C15.6201 50.801 5.38721 40.5681 5.38721 27.9452Z"
                              fill="#04CBFC" fill-opacity="0.47" shape-rendering="crispEdges" />
                          </g>
                          <path fill-rule="evenodd" clip-rule="evenodd"
                            d="M23.6894 23.2103C23.405 23.2103 23.176 22.9726 23.2092 22.6902C23.5673 19.6433 25.9394 17.375 30.1811 17.375C34.2592 17.375 37.0088 19.6711 37.0088 22.8008C37.0088 25.0677 35.8039 26.6618 33.7649 27.8172C31.7721 28.9287 31.2006 29.7038 31.2006 31.2102V31.6023C31.2006 31.8784 30.9767 32.1023 30.7006 32.1023H28.4964C28.2228 32.1023 28.0001 31.8824 27.9965 31.6089L27.9875 30.9323C27.9103 28.8702 28.8526 27.5539 30.9689 26.3547C32.838 25.2871 33.5023 24.4388 33.5023 22.9325C33.5023 21.2798 32.1274 20.066 30.0111 20.066C28.0548 20.066 26.7359 21.0883 26.4247 22.7487C26.3764 23.0064 26.1625 23.2103 25.9003 23.2103H23.6894ZM29.5865 37.625C30.7143 37.625 31.5713 36.8258 31.5713 35.7841C31.5713 34.7423 30.7143 33.9432 29.5865 33.9432C28.4889 33.9432 27.6168 34.7423 27.6168 35.7841C27.6168 36.8258 28.4889 37.625 29.5865 37.625Z"
                            fill="white" />
                          <defs>
                            <filter id="filter0_d_0_1" x="0.387207" y="0.0893555" width="60.0165" height="55.7117"
                              filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
                              <feFlood flood-opacity="0" result="BackgroundImageFix" />
                              <feColorMatrix in="SourceAlpha" type="matrix"
                                values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha" />
                              <feOffset />
                              <feGaussianBlur stdDeviation="2.5" />
                              <feComposite in2="hardAlpha" operator="out" />
                              <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0" />
                              <feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow_0_1" />
                              <feBlend mode="normal" in="SourceGraphic" in2="effect1_dropShadow_0_1" result="shape" />
                            </filter>
                          </defs>

                        </svg>
                      </li>
                  </div>
                  <div class="col-3" style="padding: 5px; margin: 10px;"></div>
                  </ul>
                </div>
                <%
                HashMap<String, Object> dataMap = (HashMap<String, Object>) request.getAttribute("dataMap");
                String searchStr = (String) dataMap.getOrDefault("search", "");
                HashMap<String, Object> result = (HashMap<String, Object>) request.getAttribute("result");
                %>
                  <br>
                  <br>
                  <br>
                  <div class="justify-content-center row">
                    <div class="row col-7" style="align-items: center;">
                      <div class="col-3">
                        <select style="border-radius: 25px;" class="form-control" name="key_name">
                          <option value="dise_name_kr" <%=(searchStr.equals("dise_name_kr")) ? "selected" : "" %>>질환명
                          </option>
                          <option value="dise_KCD_code" <%=(searchStr.equals("dise_KCD_code")) ? "selected" : "" %>
                            >KCD코드
                          </option>
                          <option value="dise_spc_code" <%=(searchStr.equals("dise_spc_code")) ? "selected" : "" %>>산정특례
                            특정기호</option>
                          <option value="dise_symptoms" <%=(searchStr.equals("dise_symptoms")) ? "selected" : "" %>>증상명
                          </option>
                          <option value=""> 증상명 </option>
                        </select>
                      </div>
                      <div class="col-7">
                        <input class="form-control" placeholder="Enter Search!" name="search_word"
                          value='<%= dataMap.getOrDefault("search_word", "") %>'>
                      </div>
                      <div class="col-2">
                        <button type="submit" style="border:none; background: none;" formaction="/info/info_raredisease"
                          formmethod="get"><svg width="50" height="50" viewBox="0 0 80 80" fill="none"
                            xmlns="http://www.w3.org/2000/svg">
                            <g filter="url(#filter0_d_7_45)">
                              <path
                                d="M5 40C5 20.67 20.67 5 40 5C59.33 5 75 20.67 75 40C75 59.33 59.33 75 40 75C20.67 75 5 59.33 5 40Z"
                                fill="#04CBFC" fill-opacity="0.47" shape-rendering="crispEdges" />
                            </g>
                            <path d="M35 50L45 40L35 30" stroke="white" stroke-width="5" stroke-linecap="round" />
                            <defs>
                              <filter id="filter0_d_7_45" x="0" y="0" width="80" height="80"
                                filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
                                <feFlood flood-opacity="0" result="BackgroundImageFix" />
                                <feColorMatrix in="SourceAlpha" type="matrix"
                                  values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha" />
                                <feOffset />
                                <feGaussianBlur stdDeviation="2.5" />
                                <feComposite in2="hardAlpha" operator="out" />
                                <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0" />
                                <feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow_7_45" />
                                <feBlend mode="normal" in="SourceGraphic" in2="effect1_dropShadow_7_45"
                                  result="shape" />
                              </filter>
                            </defs>
                          </svg></button>
                      </div>
                    </div>
                  </div>
                  <div class="text-center"
                    style="width: 10%; height: 40px; margin-bottom: 10px; border-radius: 45px; box-shadow: 0px 0px 5px rgba(0,0,0,0.1); align-items: center;">
                    <a href="#" onclick="javascript:fn_downExcel(); return false;" title=" 엑셀다운로드">엑셀다운로드</a>
                  </div>
                  <table class="table table-hover">
                    <thead>
                      <tr>
                        <th class="center-fixed">번호</th>
                        <th>병명(한국어)</th>
                        <th>병명(영어)</th>
                        <th class="center-fixed">KCD코드</th>
                        <th class="center-fixed">산정특례<br>특정기호</th>
                        <th class="center-fixed">항목분류</th>
                        <th class="center-fixed">의료비지원</th>
                        <th>상세정보</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="record" items="${resultList}" varStatus="status">
                        <tr>
                          <td class="center-fixed"><c:out value="${paginations.startRecordNumber + status.index}"/></td>
                          <td><c:out value="${record.dise_name_kr}"/></td>
                          <td><c:out value="${record.dise_name_en}"/></td>
                          <td class="center-fixed"><c:out value="${record.dise_KCD_code}"/></td>
                          <td class="center-fixed"><c:out value="${record.dise_spc_code}"/></td>
                          <td class="center-fixed"><c:out value="${record.dise_group}"/></td>
                          <td class="center-fixed"><c:out value="${record.dise_support}"/></td>
                          <td>
                            <a style="border:none; background: none;" href='<c:out value="${record.dise_url}"/>'
                              target="_blank"><svg width="61" height="32" viewBox="0 0 61 32" fill="none"
                                xmlns="http://www.w3.org/2000/svg">
                                <g filter="url(#filter0_d_39_9)">
                                  <path
                                    d="M5 16C5 9.92487 9.92487 5 16 5H45C51.0751 5 56 9.92487 56 16V16C56 22.0751 51.0751 27 45 27H16C9.92487 27 5 22.0751 5 16V16Z"
                                    fill="#04CBFC" fill-opacity="0.47" shape-rendering="crispEdges" />
                                </g>
                                <path d="M27 22L34 16L27 10" stroke="white" stroke-width="2" stroke-linecap="round" />
                                <defs>
                                  <filter id="filter0_d_39_9" x="0" y="0" width="61" height="32"
                                    filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
                                    <feFlood flood-opacity="0" result="BackgroundImageFix" />
                                    <feColorMatrix in="SourceAlpha" type="matrix"
                                      values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha" />
                                    <feOffset />
                                    <feGaussianBlur stdDeviation="2.5" />
                                    <feComposite in2="hardAlpha" operator="out" />
                                    <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0" />
                                    <feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow_39_9" />
                                    <feBlend mode="normal" in="SourceGraphic" in2="effect1_dropShadow_39_9"
                                      result="shape" />
                                  </filter>
                                </defs>
                              </svg>
                            </a>
                          </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                  <div>
                    <!-- <% Paginations paginations=(Paginations)result.get("pagination"); %> -->
                      <div>총 갯수 : <c:out value="${paginations.totalCount}"/></div>
                      </div>
                      <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <li class="page-item {{ '' if pagination.has_previous_block else 'disabled' }}">
                                <a  style="border: none; background: none;" class="page-link" href="/info/info_raredisease?currentPage=${paginations.blockStart}">
                                    <svg width="21" height="18" viewBox="0 0 21 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M18 3L12 9L18 15" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                                        <path d="M10 3L4 9L10 15" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                                    </svg>
                                </a>
                            </li>
                            <li class="page-item {{ '' if pagination.has_previous_page else 'disabled' }}">
                                <a style="border: none; background: none;" class="page-link"
                                    href="/info/info_raredisease?currentPage=${paginations.previousPage}"><svg width="13" height="18" viewBox="0 0 13 18" fill="none"
                                    xmlns="http://www.w3.org/2000/svg">
                                    <path d="M10 3L4 9L10 15" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                                </svg></a>
                            </li>

                            
                            <!-- <%
                                for(int i=paginations.getBlockStart();i <= paginations.getBlockEnd(); i=i+1){
                            %>
                            <li class=" page-item {{ 'active' if page_num == pagination.current_page else '' }}" id="pages">
                                <a style="border: none; background: none; color: black;" class="page-link" href="/info/info_raredisease?currentPage=<%= i %>">
                                    <%= i %>
                                </a>
                            </li>
                            <% } %> -->
                            <c:forEach var="i" begin="${paginations.blockStart}" end="${paginations.blockEnd}">
                              <li class="page-item ${i == paginations.currentPage ? 'active' : ''}">
                                  <a style="border: none; background: none; color: black;" class="page-link" href="/info/info_raredisease?currentPage=${i}">${i}</a>
                              </li>
                          </c:forEach>

                            <li class=" page-item {{ '' if pagination.has_next_page else 'disabled' }}">
                                <a style="border: none; background: none;" class="page-link"
                                    href="/info/info_raredisease?currentPage=${paginations.nextPage}"><svg width="13" height="18" viewBox="0 0 13 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M3 15L9 9L3 3" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                                    </svg>
                                <path d="M4 0V22" stroke="#EDEDED" stroke-width="7" />
                            </svg></a>
                            </li>
                            <li class=" page-item {{ '' if pagination.has_next_block else ' disabled' }}">
                                <a style="border: none; background: none;" class="page-link" href="/info/info_raredisease?currentPage=${paginations.blockEnd}">
                                    <svg width="21" height="18" viewBox="0 0 21 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M3 15L9 9L3 3" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                                        <path d="M11 15L17 9L11 3" stroke="#696969" stroke-width="5" stroke-linecap="round" />
                                    </svg>
                                </a>
                            </li>
                        </ul>
                    </nav>
                  </div>
              </form>
            </div>
          </div>
          <div class="col-2">
            <%@ include file="/WEB-INF/rarefield/views/commons/side_right_banner.jsp" %>
          </div>
        </main>
        <%@ include file="/WEB-INF/rarefield/views/commons/footer.jsp" %>
          <script>
            function fn_downExcel() {
              fetch('/download')
                .then(response => response.blob())
                .then(blob => {
                  // 다운로드를 위한 URL 생성
                  const url = window.URL.createObjectURL(blob);
                  // 임시 a 태그를 생성하여 다운로드 링크 설정
                  const a = document.createElement('a');
                  a.style.display = 'none';
                  a.href = url;
                  // 파일명을 설정합니다. 실제 파일명으로 변경해주세요.
                  a.download = 'data/csv/[헬프라인]희귀질환목록_2024_03_20_10_34_05.xlsx';
                  // a 태그를 body에 추가하고 클릭 이벤트를 발생시켜 다운로드
                  document.body.appendChild(a);
                  a.click();
                  // 사용 후 URL 및 a 태그 제거
                  window.URL.revokeObjectURL(url);
                  document.body.removeChild(a);
                })
                .catch(error => console.error('다운로드 중 에러가 발생했습니다.', error));
            }
          </script>

</html>