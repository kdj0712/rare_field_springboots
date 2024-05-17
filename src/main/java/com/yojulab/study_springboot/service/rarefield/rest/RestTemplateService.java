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
    public void getRequest() {
    	// 요청을 보낼 URL
        String apiUrl = "http://trainings.iptime.org:45004/trend/trend_law_data";
     
     	// HTTP GET 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        
        // 응답 값
        String responseBody = responseEntity.getBody();
        System.out.println("GET Response: " + responseBody);
    }

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

    public List<Map<String,Object>> newsPostRequest() {
    	// 요청을 보낼 URL
        String apiUrl = "http://trainings.iptime.org:45004/trend/trend_news_data";

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

    
    public List<Map<String,Object>> infodiseaseRequest() {
    	// 요청을 보낼 URL
        String apiUrl = "http://trainings.iptime.org:45004/info/info_raredisease";

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
