<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ page import="java.util.HashMap, java.util.ArrayList, com.yojulab.study_springboot.utils.Paginations" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ page import="java.util.Map, java.util.List" %>
      <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
          <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <script src="/proxy/google-maps" async defer></script>
            <%@ include file="/WEB-INF/rarefield/views/commons/header.jsp" %>
              <style>
                .name {
                  max-width: 250px;
                  /* 최대 너비 설정 */
                  word-wrap: break-word;
                  /* 길이가 최대 너비를 넘어가면 줄바꿈 */
                }

                .address {
                  max-width: 450px;
                  /* 최대 너비 설정 */
                  word-wrap: break-word;
                  /* 길이가 최대 너비를 넘어가면 줄바꿈 */
                }

                .excellent-info {
                  display: none;
                  /* 기본적으로 우수 정보를 숨김 */
                }

                .td {
                  text-align: center;
                }

                .hidden {
                  display: none;
                }

                .visible {
                  display: block;
                }
              </style>


              <main class="row justify-content-between">

                <%@ include file="/WEB-INF/rarefield/views/commons/side_left_banner.jsp" %>


                  <% HashMap<String, Object> dataMap = (HashMap<String, Object>) request.getAttribute("dataMap");
                      if (dataMap == null) {
                      dataMap = new HashMap<>();
                        }

                        String searchStr = (String) dataMap.getOrDefault("search", "");
                        HashMap<String, Object> result = (HashMap<String, Object>) request.getAttribute("results");
                            List<Map<String, Object>> resultList = null;
                              if (result != null) {
                              resultList = (List<Map<String, Object>>) result.get("results");
                                if (resultList == null) {
                                resultList = new ArrayList<>();
                                  }
                                  }

                                  Paginations paginations = null;
                                  if (result != null) {
                                  paginations = (Paginations) result.get("paginations");
                                  }

                                  int startRecordNumber = 1;
                                  if (dataMap.containsKey("StartRecordNumber")) {
                                  startRecordNumber = (int) dataMap.get("StartRecordNumber");
                                  }
                                  String keyword = request.getParameter("keyword");
                                  String pos = request.getParameter("pos");
                                  %>
                                  <div class="col-8 row">
                                    <div class="col" style="justify-items: center; width: 100%;">
                                      <form action="" id="maps" class="container">
                                        <div class="row">
                                          <div class="col-12" id="map" style="width: 100%; height: 600px;"></div>
                                          <div class="col-12">
                                            <label for="keyword">검색할 장소를 입력하세요</label>
                                            <input type="text" id="keyword" name="keyword" class="controls"
                                              placeholder="입력하기" value="${param.keyword}">
                                            <input type="hidden" id="pos" name="pos" value="<c:out value="${pos}" />">
                                            <button id="getLocation" style="display:none;">위치 정보 제공</button>
                                            <button type="button" formmethod="get"
                                              onclick="getLocationAndSubmit()">Search</button>
                                            <div class="row">
                                              <table id="places">
                                                <thead>
                                                  <tr>
                                                    <th class="td">Name</th>
                                                    <th class="td">Address</th>
                                                    <th class="td">Tel Num</th>
                                                    <th class="td">자세히 보기</th>
                                                  </tr>
                                                </thead>
                                                <tbody id="tbody">
                                                  <c:if test="${empty results}">
                                                    <tr>
                                                      <td colspan="8" class="center-fixed">검색 결과가 없습니다.</td>
                                                    </tr>
                                                  </c:if>
                                                  <c:forEach var="result" items="${results}" varStatus="status">
                                                    <tr>
                                                      <td class="name">
                                                        ${result.name}
                                                      </td>
                                                      <td class="address">
                                                        ${result.addr}
                                                      </td>
                                                      <td class="td">
                                                        ${result.telno}
                                                      </td>
                                                      <td class="td">
                                                        <a href="#" onclick="focusOnMap(${status.index}); return false;"
                                                          data-result-id=${result.ykiho}>보기</a>
                                                      </td>
                                                    </tr>
                                                    <tr>
                                                      <td colspan="4">
                                                        <div onclick="toggleExcellentInfo(${status.index});"
                                                          style="cursor: pointer;">병원평가</div>
                                                        <div id="excellent-info-${status.index}" class="excellent-info"
                                                          style="display: none;">
                                                          <c:if test="${not empty result.excellent_info}">
                                                            <ul>
                                                              <c:forEach var="info" items="${result.excellent_info}">
                                                                <li>${info}</li>
                                                              </c:forEach>
                                                            </ul>
                                                          </c:if>
                                                          <c:if test="${empty result.excellent_info}">
                                                            우수 정보 없음
                                                          </c:if>
                                                        </div>
                                                      </td>
                                                    </tr>
                                                  </c:forEach>
                                                </tbody>
                                              </table>
                                              <div>
                                                <div>총 갯수 :
                                                  <c:out value="${paginations.totalCount}" />
                                                </div>
                                              </div>
                                              <nav aria-label="Page navigation">
                                                <ul class="pagination justify-content-center">
                                                  <li
                                                    class="page-item {{ '' if pagination.has_previous_block else 'disabled' }}">
                                                    <a style="border: none; background: none;" class="page-link"
                                                      href="javascript:void(0);"
                                                      onclick="goToPage(${paginations.previousPage});return false;">
                                                      <svg width="21" height="18" viewBox="0 0 21 18" fill="none"
                                                        xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M18 3L12 9L18 15" stroke="#696969" stroke-width="5"
                                                          stroke-linecap="round" />
                                                        <path d="M10 3L4 9L10 15" stroke="#696969" stroke-width="5"
                                                          stroke-linecap="round" />
                                                      </svg>
                                                    </a>
                                                  </li>
                                                  <li
                                                    class="page-item {{ '' if pagination.has_previous_page else 'disabled' }}">
                                                    <a style="border: none; background: none;" class="page-link"
                                                      href="javascript:void(0);"
                                                      onclick="goToPage(${paginations.blockStart});return false;"><svg
                                                        width="13" height="18" viewBox="0 0 13 18" fill="none"
                                                        xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M10 3L4 9L10 15" stroke="#696969" stroke-width="5"
                                                          stroke-linecap="round" />
                                                      </svg></a>
                                                  </li>
                                                  <c:forEach var="i" begin="${paginations.blockStart}"
                                                    end="${paginations.blockEnd}">
                                                    <li
                                                      class="page-item ${i == paginations.currentPage ? 'active' : ''}">
                                                      <a style="border: none; background: none; color: black;"
                                                        class="page-link" href="javascript:void(0);"
                                                        onclick="goToPage(${i});return false;">${i}</a>
                                                    </li>
                                                  </c:forEach>

                                                  <li
                                                    class=" page-item {{ '' if pagination.has_next_page else 'disabled' }}">
                                                    <a style="border: none; background: none;" class="page-link"
                                                      href="javascript:void(0);"
                                                      onclick="goToPage(${paginations.blockEnd});return false;"><svg
                                                        width="13" height="18" viewBox="0 0 13 18" fill="none"
                                                        xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M3 15L9 9L3 3" stroke="#696969" stroke-width="5"
                                                          stroke-linecap="round" />
                                                      </svg>
                                                      <path d="M4 0V22" stroke="#EDEDED" stroke-width="7" />
                                                      </svg>
                                                    </a>
                                                  </li>
                                                  <li
                                                    class=" page-item {{ '' if pagination.has_next_block else ' disabled' }}">
                                                    <a style="border: none; background: none;" class="page-link"
                                                      href="javascript:void(0);"
                                                      onclick="goToPage(${paginations.nextPage});return false;">
                                                      <svg width="21" height="18" viewBox="0 0 21 18" fill="none"
                                                        xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M3 15L9 9L3 3" stroke="#696969" stroke-width="5"
                                                          stroke-linecap="round" />
                                                        <path d="M11 15L17 9L11 3" stroke="#696969" stroke-width="5"
                                                          stroke-linecap="round" />
                                                      </svg>
                                                    </a>
                                                  </li>
                                                </ul>
                                              </nav>
                                            </div>
                                          </div>
                                        </div>
                                      </form>
                                    </div>
                                  </div>
                                  <%@ include file="/WEB-INF/rarefield/views/commons/side_right_banner.jsp" %>
              </main>
              <hr>
              <%@ include file="/WEB-INF/rarefield/views/commons/footer.jsp" %>
                <script>
                  let map, infowindow, userPosition, service;


                  function initMap() {
                    infowindow = new google.maps.InfoWindow();

                    const userLocationConsent = localStorage.getItem('userLocationConsent');
                    const defaultPos = { lat: 37.5665, lng: 126.9780 };  // 서울의 기본 위치를 예시로 설정

                    if (userLocationConsent === 'granted' && navigator.geolocation) {
                      requestLocation();
                    } else if (userLocationConsent === null) {
                      const confirmed = confirm("이 웹사이트는 위치 정보를 사용하여 보다 나은 서비스를 제공합니다. 위치 정보를 제공하시겠습니까?");

                      if (confirmed) {
                        localStorage.setItem('userLocationConsent', 'granted');
                        document.getElementById('getLocation').style.display = 'block';
                      } else {
                        handleLocationError(false, infowindow, defaultPos);
                      }
                    } else {
                      handleLocationError(false, infowindow, defaultPos);
                    }

                    // 이벤트 리스너 추가
                    document.getElementById('getLocation').addEventListener('click', requestLocation);
                  }

                  function requestLocation() {
                    if (navigator.geolocation) {
                      navigator.geolocation.getCurrentPosition(
                        function (position) {
                          const pos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                          };
                          sessionStorage.setItem('pos', `${pos.lat},${pos.lng}`);
                          map = new google.maps.Map(document.getElementById('map'), {
                            center: pos,
                            zoom: 15,
                            draggable: true,
                            zoomControl: true,
                          });
                          displayMarkers(results);
                        },
                        function (error) {
                          console.error("Error requesting location:", error);
                          const defaultPos = { lat: 37.5665, lng: 126.9780 };  // 서울의 기본 위치를 예시로 설정
                          handleLocationError(true, infowindow, defaultPos);
                        }
                      );
                    } else {
                      alert("이 브라우저에서는 위치 정보 사용이 지원되지 않습니다.");
                    }
                  }

                  function handleLocationError(browserHasGeolocation, infowindow, pos) {
                    infowindow.setPosition(pos);
                    infowindow.setContent(browserHasGeolocation ?
                      'Error: The Geolocation service failed.' :
                      'Error: Your browser doesn\'t support geolocation.');
                    infowindow.open(map);
                  }

                  
                  async function getLocationAndSubmit() {
                    try {
                      var yPos, xPos;
                      // 위치 정보를 비동기적으로 요청하고, 결과를 기다립니다.
                      await new Promise((resolve, reject) => {
                        navigator.geolocation.getCurrentPosition(
                          (position) => {
                            yPos = position.coords.latitude.toString();
                            xPos = position.coords.longitude.toString();
                            var pos = `${yPos},${xPos}`;
                            document.getElementById('pos').value = pos;

                            console.log('Latitude:', yPos);
                            console.log('Longitude:', xPos);
                            console.log('Position set to:', pos);
                            resolve({ latitude: yPos, longitude: xPos }); // pos 값을 resolve 함수에 전달합니다.
                          },
                          (error) => {
                            console.error("Geolocation error:", error);
                            reject(error);
                          }
                        );
                      });

                      // 비동기 후처리 부분에서 pos 값을 재확인하고 폼을 제출합니다.
                      var posValue = document.getElementById('pos').value;
                      console.log('Submitting form with pos:', posValue);
                      document.getElementById('maps').submit();

                    } catch (error) {
                      // 위치 정보를 가져오는 데 실패했을 때 실행되는 부분
                      console.log('Geolocation error:', error);
                      alert('위치 정보를 가져오는 데 실패했습니다. 다시 시도해 주세요.');
                    }
                  }

                  function callback(results, status) {
                    if (status === google.maps.places.PlacesServiceStatus.OK) {
                      for (let i = 0; i < results.length; i++) {
                        createMarker(results[i]);
                      }
                    }
                  }


                  function displayMarkers(results) {
                    if (!results || results.length === 0) return;

                    results.forEach((result, index) => {
                      if (result.YPos && result.XPos) {
                        const marker = new google.maps.Marker({
                          position: { lat: parseFloat(result.YPos), lng: parseFloat(result.XPos) },
                          map: map,
                          title: result.yadmNm ? result.yadmNm : `Marker ${index + 1}`
                        });
                        google.maps.event.addListener(marker, 'click', function () {
                          infowindow.setContent(result.yadmNm ? result.yadmNm : `Marker ${index + 1}`);
                          infowindow.open(map, marker);
                        });
                      }
                    });
                  }

                  document.addEventListener("DOMContentLoaded", function () {
                    // 지도 초기화
                    initMap();

                    // URL에서 쿼리 파라미터 가져오기
                    const urlParams = new URLSearchParams(window.location.search);
                    const keyword = urlParams.get('keyword');
                    const pos = urlParams.get('pos');

                    // 폼 입력 필드에 값 설정하기
                    if (keyword !== null) {
                      document.getElementById('keyword').value = keyword;
                    }
                    if (pos !== null && pos !== ',') {
                      document.getElementById('pos').value = pos;
                    }

                    // 이벤트 리스너 추가
                    document.getElementById('getLocation').addEventListener('click', getLocationAndSubmit);
                  });
                </script>