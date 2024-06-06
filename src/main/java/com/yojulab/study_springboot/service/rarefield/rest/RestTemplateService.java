package com.yojulab.study_springboot.service.rarefield.rest;
import com.yojulab.study_springboot.utils.Paginations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
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



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    public Map<String, Object> institutionSearch(Integer currentPage, String keyword, String pos) throws Exception {
        String baseUrl = "http://rare-field.shop/info/institution?";
        String decodedBaseUrl = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("page_number", currentPage)
                .queryParam("keyword", keyword)
                .queryParam("pos", pos)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(decodedBaseUrl, entity, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);

        Map<String, Object> result = new HashMap<>();
        if (responseMap.containsKey("results")) {
            List<Map<String, Object>> results = (List<Map<String, Object>>) responseMap.get("results");
            result.put("results", results);
        }

        if (responseMap.containsKey("pagination")) {
            Map<String, Object> paginationMap = (Map<String, Object>) responseMap.get("pagination");
            result.put("pagination", paginationMap);
        }

        return result;
    }


    // public Map<String, Object> institutionSearch(Integer currentPage, String keyword, String pos) throws JsonProcessingException {
    //     // 기본 URL 설정
    //     String baseUrl = "http://rare-field.shop/info/institution?";

    //     String decodedBaseUrl = URLDecoder.decode(baseUrl, StandardCharsets.UTF_8);
        
    //     // 파라미터 추가 (키워드와 위치)
    //     // UriComponentsBuilder 초기화
    //     UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(decodedBaseUrl);

    //     if (currentPage != null) {
    //         builder.queryParam("page_number", currentPage);
    //     }
    //     if (keyword != null && !keyword.isEmpty()) {
    //         builder.queryParam("keyword", keyword);
    //     }
    //     if (pos != null && !pos.isEmpty()) {
    //         builder.queryParam("pos", pos);
    //     }

    //     // HttpHeaders 객체 생성 및 Content-Type 설정
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    //     headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    //     headers.set("Accept-Encoding", "gzip, deflate, br");
    //     headers.set("Connection", "keep-alive");

    //     HttpEntity<String> entity = new HttpEntity<>(headers);

    //     // RestTemplate 초기화
    //     RestTemplate restTemplate = new RestTemplate();

    //     // 결과를 저장할 Map 생성
    //     Map<String, Object> result = new HashMap<>();
    //     String encodedUrl = builder.toUriString();
    //     try {
    //         // 요청 및 응답
    //         ResponseEntity<String> responseEntity = restTemplate.postForEntity(encodedUrl, entity, String.class);
    //         String responseBody = responseEntity.getBody();

    //         // ObjectMapper 초기화 및 설정
    //         ObjectMapper objectMapper = new ObjectMapper();
    //         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    //         Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);

    //         if (responseMap.containsKey("results")) {
    //             List<Map<String, Object>> results = (List<Map<String, Object>>) responseMap.get("results");
    //             result.put("results", results);
    //         }
    //         if (responseMap.containsKey("pagination")) {
    //             Map<String, Object> paginationMap = (Map<String, Object>) responseMap.get("pagination");
    //             result.put("pagination", paginationMap);
    //         }
    //     } catch (RestClientException e) {
    //         // 예외 처리
    //         result.put("error", "Failed to fetch data: " + e.getMessage());
    //     }

    //     return result;
    // }
    
    public Map<String, Object> dise_search(Integer currentPage, String key_name, String search_word) throws JsonProcessingException {
        // 기본 URL 설정
        String baseUrl = "http://rare-field.shop/info/raredisease?";
        
        // URL 디코딩
        String decodedBaseUrl = URLDecoder.decode(baseUrl, StandardCharsets.UTF_8);

        // UriComponentsBuilder 초기화
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(decodedBaseUrl);

        // 조건에 따라 URL에 파라미터 추가
        if (currentPage != null) {
            builder.queryParam("page_number", currentPage);
        }
        if (key_name != null && !key_name.isEmpty()) {
            builder.queryParam("key_name", key_name);
        }
        if (search_word != null && !search_word.isEmpty()) {
            builder.queryParam("search_word", search_word);
        }

        // HttpHeaders 객체 생성 및 Content-Type 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("Connection", "keep-alive");

        // HttpEntity 객체 생성 (여기서는 본문을 비워둘 수 있습니다)
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // RestTemplate 초기화
        RestTemplate restTemplate = new RestTemplate();

        // 결과를 저장할 Map 생성
        Map<String, Object> result = new HashMap<>();
        String encodedUrl = builder.toUriString();

        try {
            // 요청 및 응답
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(encodedUrl, entity, String.class);
            String responseBody = responseEntity.getBody();

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
