package com.yojulab.study_springboot.service.rarefield.rest;
import com.yojulab.study_springboot.utils.Paginations;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;


@Service
public class RestTemplateService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper mapper;

    // private RestTemplate restTemplate = new RestTemplate();
    // private ObjectMapper mapper = new ObjectMapper(); 

    // public void RestTemplateController(@Autowired RestTemplate restTemplate) {
    //     this.restTemplate = restTemplate;
    // }
    // public void getRequest() {
    // 	// 요청을 보낼 URL
    //     String apiUrl = "http://trainings.iptime.org:45004/trend/trend_law_data";
     
    //  	// HTTP GET 요청 보내기
    //     ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        
    //     // 응답 값
    //     String responseBody = responseEntity.getBody();
    //     System.out.println("GET Response: " + responseBody);
    // }

    public List<Map<String,Object>> lawPostRequest() {
    	// 요청을 보낼 URL
        String apiUrl = "http://rare-field.shop/trend/trend_law_data";

		// HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		
        // 요청 데이터 생성
        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();
		    requestData.add("key", "value");

		// HTTP POST 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestData, String.class);
        
        // 응답 값
        String responseBody = responseEntity.getBody();
        // System.out.println("POST Response: " + responseBody);

        String jsonString = responseBody;
    
        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject = new JSONObject(jsonString);
        // 배열을 가져옵니다.
        JSONArray jArray = jObject.getJSONArray("trend_law");
       
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject obj = jArray.getJSONObject(i);
            Map<String, Object> map = new HashMap<>();
        
            for(String key : obj.keySet()) {
                Object value = obj.get(key);
                map.put(key, value);
            }
        
            list.add(map);
        }
        return list;
		

    }

    public Map<String,Object> newsPostRequest(int currentPage) {
    	// 요청을 보낼 URL
        String apiUrl = "http://rare-field.shop/trend/trend_news_data?page_number=" + currentPage;

		// HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		
        // 요청 데이터 생성
        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();

		requestData.add("key", "value");

		// HTTP POST 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestData, String.class);
        
        // 응답 값
        String responseBody = responseEntity.getBody();
        // System.out.println("POST Response: " + responseBody);

        String jsonString = responseBody;
       
        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject = new JSONObject(jsonString);
        // 배열을 가져옵니다.
        JSONArray jArray = jObject.getJSONArray("news");
    
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject obj = jArray.getJSONObject(i);
            Map<String, Object> map = new HashMap<>();
        
            for(String key : obj.keySet()) {
                Object value = obj.get(key);
                map.put(key, value);
            }
        
            list.add(map);
        }

        JSONObject paginationObject = jObject.getJSONObject("pagination");
        Paginations paginations = new Paginations(paginationObject.getInt("total_records"), paginationObject.getInt("current_page"));

        Map<String,Object> result = new HashMap<>();
        result.put("news",list);
        result.put("pagination",paginations);

        return result;

    }

    public Map<String,Object> newsReadGetRequest(String id) {
    	// 요청을 보낼 URL
        String apiUrl = "http://rare-field.shop/trend/trend_news_data/" + id;

		// HTTP POST 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        
        // 응답 값
        String responseBody = responseEntity.getBody();
        System.out.println("get Response: " + responseBody);

        String jsonString = responseBody;
       
        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject = new JSONObject(jsonString);
        JSONObject newsObject = jObject.getJSONObject("news");

        Map<String,Object> result = new HashMap<>();
        result.put("id",newsObject.optString("_id"));
        result.put("news_title",newsObject.optString("news_title"));
        result.put("news_datetime",newsObject.optString("news_datetime"));
        result.put("news_contents",newsObject.optString("news_contents"));
        result.put("news_url",newsObject.optString("news_url"));
        result.put("news_topic",newsObject.optString("news_topic"));
        result.put("news_paper",newsObject.optString("news_paper"));
        result.put("news_image",newsObject.optString("news_image"));

        return result;

    }


    public Map<String, Object> institutionQueryRequest(String keyword, double latitude, double longitude, int selectedPage) throws JsonProcessingException {
        // 기본 URL 설정
        String baseUrl = "http://rare-field.shop/info/institution";
        
        // 선택된 페이지 번호를 URL에 추가
        String apiUrl = baseUrl + "?";
        
        // 파라미터 추가 (키워드와 위치)
        String params = "keyword=" + URLEncoder.encode(keyword, StandardCharsets.UTF_8) + "&pos=" + latitude + "," + longitude;
        apiUrl += params;
        
        // 요청 본문에 들어갈 맵 생성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("keyword", keyword);
        requestBody.put("pos", latitude + "," + longitude);
        requestBody.put("page_number", selectedPage);
        
        // HttpHeaders 객체 생성 및 Content-Type 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // HttpEntity 객체 생성
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        
        // POST 요청 보내기
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
        
        // 응답 본문을 Map으로 변환
        Map<String, Object> responseMap = mapper.readValue(response.getBody(), Map.class);
        
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) responseMap.get("results");
        Map<String, Object> paginationMap = (Map<String, Object>) responseMap.get("pagination");
        
        Map<String, Object> result = new HashMap<>();
        result.put("results", resultList);
        result.put("pagination", paginationMap);
        
        return result;
    }
    
    public Map<String, Object> dise_search(Integer currentPage, String key_name, String search_word) throws JsonProcessingException {
        // 기본 URL 설정
        String baseUrl = "http://rare-field.shop/info/raredisease";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
    
        // 조건에 따라 URL에 파라미터 추가
        if (currentPage != null) {
            builder.queryParam("page_number", currentPage);
        }
        if (key_name != null && !key_name.isEmpty()) {
            builder.queryParam("key_name", key_name);
        }
        if (search_word != null && !search_word.isEmpty()) {
            // search_word를 명시적으로 인코딩
            builder.queryParam("search_word", search_word);
        }
    
        // HttpHeaders 객체 생성 및 Content-Type 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    
        // HttpEntity 객체 생성 (여기서는 본문을 비워둘 수 있습니다)
        HttpEntity<String> entity = new HttpEntity<>(headers); 
    
        // RestTemplate 초기화
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        
        // 결과를 저장할 Map 생성
        Map<String, Object> result = new HashMap<>();
    
        try {
            // 요청 및 응답
            String responseBody = restTemplate.postForObject(builder.toUriString(), entity, String.class);
    
            // ObjectMapper 초기화 및 설정
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
    
            if (responseMap.containsKey("dise_list")) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) responseMap.get("dise_list");
                result.put("results", resultList);
            }
            if (responseMap.containsKey("pagination")) {
                Map<String, Object> paginationMap = (Map<String, Object>) responseMap.get("pagination");
                result.put("pagination", paginationMap);
            }
        } catch (RestClientException e) {
            // 예외 처리
            result.put("error", "Failed to fetch data: " + e.getMessage());
        }
    
        return result;
    }
    

    

    private String postForString(String url, MultiValueMap<String, String> requestData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestData, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        return responseEntity.getBody();
    }

    private String getForString(String url) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        return responseEntity.getBody();
    }

    // public void newsReadGetRequest(List<Map<String, Object>> list, String targetKey, String targetValue) {
    //     for (Map<String, Object> map : list) {
    //         if (targetValue.equals(map.get(targetKey))) {
    //             for (Map.Entry<String, Object> entry : map.entrySet()) {
    //                 if (!entry.getKey().equals(targetKey)) {
    //                     System.out.println(entry.getKey() + ": " + entry.getValue());
    //                 }
    //             }
    //         }
    //     }
    // }
}
