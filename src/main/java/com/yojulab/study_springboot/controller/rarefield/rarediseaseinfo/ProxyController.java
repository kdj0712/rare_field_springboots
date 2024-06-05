package com.yojulab.study_springboot.controller.rarefield.rarediseaseinfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
@Controller
public class ProxyController {

    @Value("${google.maps.api.key}")
    private String googleApiKey;

    private final RestTemplate restTemplate;

    public ProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/proxy/google-maps")
    public ResponseEntity<String> proxyGoogleMaps() {
        String apiUrl = "https://maps.googleapis.com/maps/api/js";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("key", googleApiKey)
                .queryParam("callback", "initMap")
                .queryParam("language", "ko");


        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                uriBuilder.toUriString(), HttpMethod.GET, entity, String.class);

        return ResponseEntity.ok()
                .header("Content-Type", "application/javascript")
                .body(response.getBody());
    }
}
// @RestController
// public class GoogleMapsProxyController {

//     @Autowired
//     private RestTemplate restTemplate;

//     // Google Maps API URL
//     private static final String GOOGLE_MAPS_API_BASE_URL = "https://maps.googleapis.com/maps/api";

//     // Google Maps API 키 (본인의 키로 대체)
//     private static final String GOOGLE_MAPS_API_KEY = "YOUR_API_KEY";

//     @GetMapping("/maps/{endpoint}")
//     public ResponseEntity<?> proxyGoogleMaps(@PathVariable String endpoint,
//                                              @RequestParam(required = false) Map<String, String> params) {
//         StringBuilder url = new StringBuilder(GOOGLE_MAPS_API_BASE_URL);
//         url.append("/").append(endpoint);
//         url.append("?key=").append(GOOGLE_MAPS_API_KEY);

//         // 요청 파라미터 추가
//         if (!params.isEmpty()) {
//             params.forEach((key, value) -> url.append("&").append(key).append("=").append(value));
//         }

//         // HTTP 요청 생성
//         HttpHeaders headers = new HttpHeaders();
//         HttpEntity<?> requestEntity = new HttpEntity<>(headers);

//         // Google Maps API 호출
//         ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, requestEntity, String.class);

//         // 응답 전달
//         return ResponseEntity.ok(responseEntity.getBody());
//     }
// }