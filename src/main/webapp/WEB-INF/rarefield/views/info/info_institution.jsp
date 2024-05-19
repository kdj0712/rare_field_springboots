<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ page import="java.util.HashMap, java.util.ArrayList, com.yojulab.study_springboot.utils.Paginations" %>
    <%@ page import="java.util.Map, java.util.List" %>
    <%
    // request에서 result 객체를 가져옵니다.
    Map<String, Object> result = (Map<String, Object>) request.getAttribute("result");
    // result 객체가 null인 경우, 새로운 HashMap 객체를 생성하여 할당합니다.
    if (result == null) {
        result = new HashMap<>();
    }
    %>
      

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

</style>




<main class="row justify-content-between">

    <%@ include file="/WEB-INF/rarefield/views/commons/side_left_banner.jsp" %>

    <div class="col-8 row">
      <div class="col" style="justify-items: center; width: 100%;">
        <form action="" id="maps" class="container" >
          <div class="row" style="border-style: dotted;" >
            <div class="col-12" id="map" style="width: 100%; height: 600px;"></div>
            <div class="col-12">
              <label for="keyword">검색할 장소를 입력하세요</label>
        <input type="text" id="keyword" name="keyword" class="controls" placeholder="입력하기"
          value="<%= request.getParameter("keyword") %>">
        <input type="hidden" id="pos" name="pos" value="<%= request.getParameter("pos") %>">
        <button type="submit" formmethod="get" onclick="getLocationAndSubmit()">Search</button>
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
              <% ArrayList resultList=(ArrayList)result.get("resultList"); for(int i=0; i < resultList.size();
              i=i+1) { HashMap record=(HashMap)resultList.get(i); %>
              <tr>
                <td class="name">
                  <%= record.get("yadmNm") %>
                </td>
                <td class="address">
                  <%= record.get("addr") %>
                  <!-- {{result.addr}} -->
                </td>
                <td class="td">
                  <%= record.get("telno") %>
                  <!-- {{result.telno}} -->
                  
                </td>
                <td class="td">
                  <a href="#" onclick="focusOnMap(<%= i %>); return false;" data-result-id="<%= record.get("ykiho") %>">보기</a>
                </td>
              </tr>
              <tr>
                <td colspan="4">
                  <div onclick="toggleExcellentInfo(<%= i %>);" style="cursor: pointer;"  >병원평가</div>
                  <div id="excellent-info-<%= i %>" class="excellent-info" style="display: none;">
                    <% if (record.get("excellent_info") != null && !((List) record.get("excellent_info")).isEmpty()) {
                        List<String> excellentInfo = (List<String>) record.get("excellent_info");
                        for (String info : excellentInfo) {
                    %>
                    <ul>
                        <li><%= info %></li>
                    </ul>
                    <%   }
                       } else {
                    %>
                    우수 정보 없음
                    <% } %>
                </div>
                </td>
              </tr>
              <% } %>
            </tbody>
          </table>
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

    <script type="text/javascript">
      var resultsFromPython = {{ results | tojson | safe }};
    </script>
    <script>
      let map, service, infowindow, pos, yPos, xPos;
      function callback(results, status) {
        console.log(results);
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          for (let i = 0; i < results.length; i++) {
            createMarker(results[i]);
          }
        }
      }
      function getLocation() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(sendPositionToServer, showError);
        } else {
          alert("Geolocation is not supported by this browser.");
        }
      }
      function getLocationAndSubmit() {
        navigator.geolocation.getCurrentPosition(
          async function (position) {
            const yPos = position.coords.latitude.toString();
            const xPos = position.coords.longitude.toString();
            const pos = `${yPos},${xPos}`;
            document.getElementById('pos').value = pos;
            const keyword = document.getElementById('keyword').value;
            document.getElementById('maps').submit();
          },
          function (error) {
            console.error('Geolocation error:', error);
            alert('위치 정보를 가져오는 데 실패했습니다. 다시 시도해 주세요.');
          }
        );
      }
    
    
    
      function initMap() {
        infowindow = new google.maps.InfoWindow();
    
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function (position) {
            pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };
    
            map = new google.maps.Map(document.getElementById('map'), {
              center: pos,
              zoom: 15,
              draggable: true, // 지도 드래그 가능
              zoomControl: true,
            });
            callback(resultsFromPython, google.maps.places.PlacesServiceStatus.OK);
          }, function () {
            handleLocationError(true, infowindow, map.getCenter());
          });
        } else {
          handleLocationError(false, infowindow, map.getCenter());
        }
    
        // 병원 위치를 마커로 지도에 추가하는 부분
        // 'hospitals' 배열에 병원 데이터가 있다고 가정
        hospitals.forEach(function (hospital) {
          var marker = new google.maps.Marker({
            position: { lat: parseFloat(hospital.YPos), lng: parseFloat(hospital.XPos) },
            map: map,
            title: hospital.yadmNm
          });
        });
    
      }
      function toggleExcellentInfo(index) {
        var infoElement = document.getElementById('excellent-info-' + index);
        if (infoElement.style.display === 'none') {
          infoElement.style.display = 'block';
        } else {
          infoElement.style.display = 'none';
        }
      }
    
      function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        pos = { lat: 37.5665, lng: 126.9780 };
        map = new google.maps.Map(document.getElementById('map'), {
          center: pos,
          zoom: 15,
        });
    
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
          'Error: The Geolocation service failed.' :
          'Error: Your browser doesn\'t support geolocation.');
        infoWindow.open(map);
      }
      let markers = [];
    
      function createMarker(results) {
        if (!results.geometry || !results.geometry.location) return;
        const marker = new google.maps.Marker({
          map,
          position: results.geometry.location,
        });
    
        google.maps.event.addListener(marker, 'click', function () {
          infowindow.setContent(results.name);
          infowindow.open(map, this);
        });
      }
      function displayResultsOnMap(results, map) {
        results.forEach((hospital) => {
          const marker = new google.maps.Marker({
            position: { lat: hospital.YPos, lng: hospital.XPos }, // 수정됨
            map: map,
            title: hospital.yadmNm // 수정됨
          });
          markers.push(marker);
    
          marker.addListener('click', () => {
            const infoWindow = new google.maps.InfoWindow({
              content: `${hospital.yadmNm}<br> ${hospital.addr}` // 수정됨
            });
            infoWindow.open(map, marker);
          });
    
        });
      }
    
      function requestUserLocation() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition((position) => {
            const userPos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude,
            };
    
            map = new google.maps.Map(document.getElementById("map"), {
              zoom: 13,
              center: userPos,
            });
            displayResultsOnMap(resultsFromPython, map);
          });
        }
      }
    
      function focusOnMap(index, map) {
        const marker = markers[index]; // 전역 마커 배열에서 마커를 가져옴
        map.setCenter(marker.getPosition());
        map.setZoom(16); // 확대 수준을 조정할 수 있음
      }
    
      window.onload = requestUserLocation;
    </script>
    <!-- Google Maps API Script -->
    <script async
      src="https://maps.googleapis.com/maps/api/js?key={{ API_KEY }}&libraries=places&callback=initMap&language=ko"></script>
    </html>