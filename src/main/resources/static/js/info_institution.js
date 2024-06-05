// Python에서 전달된 results 데이터를 JavaScript 변수로 변환
var resultsFromPython = {{ results | tojson | safe }};

let map, service, infowindow;
const defaultPos = { lat: 37.5665, lng: 126.9780 }; // 기본 위치 (서울 좌표)

function callback(results, status) {
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

    const userLocationConsent = localStorage.getItem('userLocationConsent');

    if (userLocationConsent === 'granted' && navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            const pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            map = new google.maps.Map(document.getElementById('map'), {
                center: pos,
                zoom: 15,
                draggable: true,
                zoomControl: true,
            });
            displayMarkers(resultsFromPython);
        }, function () {
            handleLocationError(true, infowindow, defaultPos);
        });
    } else if (userLocationConsent === null) {
        const confirmed = confirm("이 웹사이트는 위치 정보를 사용하여 보다 나은 서비스를 제공합니다. 위치 정보를 제공하시겠습니까?");

        if (confirmed && navigator.geolocation) {
            localStorage.setItem('userLocationConsent', 'granted');
            navigator.geolocation.getCurrentPosition(function (position) {
                const pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };

                map = new google.maps.Map(document.getElementById('map'), {
                    center: pos,
                    zoom: 15,
                    draggable: true,
                    zoomControl: true,
                });
                displayMarkers(resultsFromPython);
            }, function () {
                handleLocationError(true, infowindow, defaultPos);
            });
        } else if (!confirmed) {
            handleLocationError(false, infowindow, defaultPos);
        } else {
            handleLocationError(false, infowindow, defaultPos);
        }
    } else {
        handleLocationError(false, infowindow, defaultPos);
    }
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

function createMarker(place) {
    if (!place.geometry || !place.geometry.location) return;
    const marker = new google.maps.Marker({
        map,
        position: place.geometry.location,
    });

    google.maps.event.addListener(marker, 'click', function () {
        infowindow.setContent(place.name);
        infowindow.open(map, this);
    });
}

// function displayMarkers(results) {
//     if (results.length === 0) return;

//     markers.forEach((marker) => marker.setMap(null)); // 기존 마커 제거
//     markers = [];

//     results.forEach((result) => {
//         const marker = new google.maps.Marker({
//             position: { lat: parseFloat(result.YPos), lng: parseFloat(result.XPos) },
//             map: map,
//             title: result.yadmNm
//         });
//         markers.push(marker);

//         marker.addListener('click', () => {
//             infowindow.setContent(`${result.yadmNm}<br>${result.addr}`);
//             infowindow.open(map, marker);
//         });
//     });
// }
function displayMarkers(results) {
    if (results.length === 0) return;

    results.forEach((result) => {
        if(result.YPos && result.XPos) {
            const marker = new google.maps.Marker({
                position: { lat: parseFloat(result.YPos), lng: parseFloat(result.XPos) },
                map: map,
                title: result.yadmNm
            });
            markers.push(marker);

            marker.addListener('click', () => {
                infowindow.setContent(`${result.yadmNm}<br>${result.addr}`);
                infowindow.open(map, marker);
            });
        }
    });
}
function focusOnMap(index) {
    const marker = markers[index];
    map.setCenter(marker.getPosition());
    map.setZoom(16);
}

window.onload = initMap;
window.onload = function () {
    const urlParams = new URLSearchParams(window.location.search);

    if (urlParams.has('keyword')) {
        document.getElementById('keyword').value = urlParams.get('keyword');
    }
};
<script src="https://maps.googleapis.com/maps/api/js?key={{ API_KEY }}&libraries=places&callback=initMap&language=ko"
    async defer></script>
