package com.yojulab.study_springboot.service.rarefield.rest;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yojulab.study_springboot.utils.Paginations;

import org.springframework.web.bind.annotation.RequestBody;


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

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper(); 

     public void RestTemplateController(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
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

    
    public List<Map<String, Object>> infodiseaseRequest() {
    	// 요청을 보낼 URL
        String apiUrl = "http://trainings.iptime.org:45004/info/raredisease";

		// HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		
        // 요청 데이터 생성
        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();
		    requestData.add("key_name", "search_word");

		// HTTP POST 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestData, String.class);
        
        // 응답 값
        String responseBody = responseEntity.getBody();
        // System.out.println("POST Response: " + responseBody);

        String jsonString = responseBody;
       
        // 가장 큰 JSONObject를 가져옵니다.
        JSONObject jObject = new JSONObject(jsonString);
        // 배열을 가져옵니다.
        JSONArray jArray = jObject.getJSONArray("data_news");
    
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

    public class MapQueryService {
        private RestTemplate restTemplate = new RestTemplate();
        public Map<String, Object> institutionQueryRequest(String keyword, double latitude, double longitude, int currentPage) {
            // 요청을 보낼 URL
            String apiUrl = "http://trainings.iptime.org:45004/info/institution/2?keyword=" + keyword + "&pos=" + latitude + "%2C" + longitude + "&page_number=" + currentPage;
            // HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);    
            // HTTP GET 요청 보내기
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);    
            // 응답 값
            String responseBody = responseEntity.getBody();    
            JSONObject jObject = new JSONObject(responseBody);    
            // 결과 배열을 가져옵니다.
            JSONArray jResults = jObject.getJSONArray("results");
            List<Map<String, Object>> resultList = new ArrayList<>();
    
            for (int i = 0; i < jResults.length(); i++) {
                JSONObject obj = jResults.getJSONObject(i);
                Map<String, Object> map = new HashMap<>();
    
                for (String key : obj.keySet()) {
                    Object value = obj.get(key);
                    map.put(key, value);
                }
    
                resultList.add(map);
            }
    
            JSONObject paginationObject = jObject.getJSONObject("pagination");
            Map<String, Object> paginationMap = new HashMap<>();
            for (String key : paginationObject.keySet()) {
                Object value = paginationObject.get(key);
                paginationMap.put(key, value);
            }
    
            Map<String, Object> result = new HashMap<>();
            result.put("results", resultList);
            result.put("pagination", paginationMap);
    
            return result;
        }
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

