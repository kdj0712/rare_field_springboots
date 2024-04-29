<!-- {% extends "mainpage.html" %}

{% block main_container %}
<form action="" method=get>
  <div class="container">
    <div class="row">
      <div class="col-6" id="map" style="width: 600px; height: 600px;"></div>
      <div class="col-6">
        <label for="keyword">검색할 장소를 입력하세요</label>
        <input type="text" id="keyword" name="keyword" class="controls" placeholder="입력하기"
          value="{{request._query_params.keyword}}">
        <button type="submit">Search</button> -->
<!-- onclick="searchPlaces()"></onclick> -->
<!-- <div class="row">
          <table id="places">
            <thead>
              <tr>
                <th>Name</th>
                <th>Address</th>
              </tr>
            </thead>
            <tbody id="tbody"></tbody>
            {% for result in results %}
            <tr>
              <td>{{result.name}}</td>
              <td>{{result.formatted_address}}</td>
            </tr>
            {% endfor %}
          </table>
        </div>
      </div>
    </div>
</form> -->
{% extends "mainpage.html" %}
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

{% block main_container %}
<form  action="/search_location" id="searchForm">
  <div class="container">
    <div class="row">
      <div class="col-6" id="map" style="width: 500px; height: 500px;"></div>
      <div class="col-6">
        <label for="keyword">검색할 장소를 입력하세요</label>
        <input type="text" id="keyword" name="keyword" class="controls" placeholder="입력하기"
          value="{{request._query_params.keyword}}">
        <!-- Hidden fields for latitude and longitude -->
        <input type="hidden" id="pos" name="pos">
        <button type="button" formmethod="get" onclick="getLocationAndSubmit()">Search</button>
        <div class="row">
          <table id="places">
            <thead>
              <tr>
                <th>Name</th>
                <th>Address</th>
              </tr>
            </thead>
            <tbody id="tbody"></tbody>
            {% for result in results %}
            <tr>
              <td>{{result.name}}</td>
              <td>{{result.formatted_address}}</td>
            </tr>
            {% endfor %}
          </table>
        </div>
      </div>
    </div>
</form>

<script type="text/javascript">
  // Python에서 전달된 results 데이터를 JavaScript 변수로 변환
  var resultsFromPython = {{ results | tojson | safe }};
</script>

<script>
  let map;
  let service;
  let infowindow;
  let pos;

  function getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(sendPositionToServer, showError);
    } else {
      alert("Geolocation is not supported by this browser.");
    }
  }

  function getLocationAndSubmit() {
    navigator.geolocation.getCurrentPosition(
        function(position) {
            const pos = `${position.coords.latitude},${position.coords.longitude}`;
            document.getElementById('pos').value = pos; // pos 필드에 위치 정보를 설정
            document.getElementById('searchForm').submit(); // 위치 데이터가 채워진 후 폼 제출
        },
        function(error) {
            console.error('Geolocation error:', error);
            alert('위치 정보를 가져오는 데 실패했습니다. 다시 시도해 주세요.');
        }
    );
}

  function sendPositionToServer(position) {
    const pos = {
      latitude: position.coords.latitude,
      longitude: position.coords.longitude
    };

    fetch('/api/send_location', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(pos)
    })
      .then(response => response.json())
      .then(data => console.log('Success:', data))
      .catch((error) => console.error('Error:', error));
  }

  function showError(error) {
    switch (error.code) {
      case error.PERMISSION_DENIED:
        alert("User denied the request for Geolocation.");
        break;
      case error.POSITION_UNAVAILABLE:
        alert("Location information is unavailable.");
        break;
      case error.TIMEOUT:
        alert("The request to get user location timed out.");
        break;
      case error.UNKNOWN_ERROR:
        alert("An unknown error occurred.");
        break;
    }
  }
  function initMap() {
    infowindow = new google.maps.InfoWindow();
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        const userLocation = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };

        map = new google.maps.Map(document.getElementById('map'), {
          center: userLocation,
          zoom: 15,
          draggable: true, // 지도 드래그 가능
          zoomControl: true,
        });
      }, () => {
        // 위치 접근 권한이 거부되었거나 위치 정보를 가져올 수 없는 경우
        handleLocationError(true, map);
      });
    } else {
      // 브라우저가 위치 정보를 지원하지 않는 경우
      handleLocationError(false, map);
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
        position: { lat: hospital.lat, lng: hospital.lng },
        map: map,
        title: hospital.name
      });

      marker.addListener('click', () => {
        const infoWindow = new google.maps.InfoWindow({
          content: `Name: ${hospital.name}<br>Address: ${hospital.address}`
        });
        infoWindow.open(map, marker);
      });
    });

    // 검색어가 특별한 병원 이름일 경우 해당 결과를 제일 먼저 보여줍니다
    results.sort((a, b) => {
      if (a.name === userInputHospitalName) return -1;
      if (b.name === userInputHospitalName) return 1;
      return 0;
    });
  }

  // function searchAndDisplay(hospitalName, pos) {
  //   fetch(`/info_institutions`, {
  //     method: 'POST',
  //     headers: {
  //       'Content-Type': 'application/json'
  //     },
  //     body: JSON.stringify({ hospital_name: hospitalName, pos: pos })
  //   })
  //     .then(response => response.json())
  //     .then(data => {
  //       displayResultsOnMap(data.results, map); // 'map' should be initialized Google Maps object
  //     })
  //     .catch(error => console.error('Error:', error));
  // }
  // function clearMarkers() {
  //   markers.forEach((marker) => marker.setMap(null));
  //   markers = [];
  // }
</script>
<!-- Google Maps API Script -->
<script src="https://maps.googleapis.com/maps/api/js?key={{ API_KEY }}&libraries=places&callback=initMap&language=ko"
  async defer></script>
{% endblock %}