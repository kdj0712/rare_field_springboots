package com.yojulab.study_springboot.service.rarefield.rest;
import com.yojulab.study_springboot.utils.Paginations;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.apache.catalina.util.URLEncoder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


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
        String apiUrl = "http://trainings.iptime.org:45004/trend/trend_law_data";

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
        String apiUrl = "http://trainings.iptime.org:45004/trend/trend_news_data?page_number=" + currentPage;

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

        // Map<String, Object> pagi = new HashMap<>();
        // pagi.put("pageScale",paginations.getPageScale());
        // pagi.put("pageBegni",paginations.getPageBegin());

        Map<String,Object> result = new HashMap<>();
        result.put("news",list);
        result.put("pagination",paginations);

        return result;

    }


    public Map<String, Object> institutionQueryRequest(String keyword, double latitude, double longitude, int selectedPage) throws JsonProcessingException {
        // 기본 URL 설정
        String baseUrl = "http://trainings.iptime.org:45004/info/institution";
        
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
        String baseUrl = "http://rare-field.shop/info/raredisease?";
        
        // // 파라미터를 MultiValueMap에 추가
        // MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        // params.add("page_number", currentPage != null ? String.valueOf(currentPage) : null);
        
        // // 사용자가 입력한 값을 파라미터에 추가
        // if (key_name != null && !key_name.isEmpty()) {
        //     params.add("key_name", key_name);
        // }
        // if (search_word != null && !search_word.isEmpty()) {
        //     params.add("search_word", search_word);
        // }

        Map<String, Object> params = new HashMap<>();
        params.put("page_number", currentPage);
        params.put("key_name", key_name);
        params.put("search_word", search_word);
        
        // Map 객체를 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParams = objectMapper.writeValueAsString(params);
        
        // HttpHeaders 객체 생성 및 Content-Type 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HttpEntity 객체 생성 (JSON 문자열과 헤더 포함)
        HttpEntity<String> entity = new HttpEntity<>(jsonParams, headers);
        
        // MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();
        
        // requestData.add("key", "value");
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);
        Map<String, Object> responseMap = objectMapper.readValue(response.getBody(), Map.class);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) responseMap.get("dise_list");
        Map<String, Object> paginationMap = (Map<String, Object>) responseMap.get("pagination");
        
        // 결과를 저장할 Map 생성
        Map<String, Object> result = new HashMap<>();
        result.put("results", resultList);
        result.put("pagination", paginationMap);

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

    public void newsReadPostRequest(List<Map<String, Object>> list, String targetKey, String targetValue) {
        for (Map<String, Object> map : list) {
            if (targetValue.equals(map.get(targetKey))) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (!entry.getKey().equals(targetKey)) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                }
            }
        }
    }
}
