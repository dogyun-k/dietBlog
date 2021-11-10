package com.example.hustar.api;

import com.example.hustar.domain.FlaskResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FlaskApi {

    public String getBase64String(MultipartFile multipartFile) throws Exception {
        byte[] bytes = multipartFile.getBytes();

        return Base64.getEncoder().encodeToString(bytes);
    }

    public FlaskResponseDto requestToFlask(String fileName, MultipartFile file) throws Exception {
        String url = "http://localhost:5000/calorie";
        RestTemplate restTemplate = new RestTemplate();

        // Header set
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Body set
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        String imageFileString = getBase64String(file);
        body.add("filename", fileName);
        body.add("image", imageFileString);

        // Message
        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

        // Request
        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);
        System.out.println("\n\n" + response.getBody() + "\n\n");

        // Response 파싱
        FlaskResponseDto dto = new FlaskResponseDto();

        try {
            JSONObject jsonObject = new JSONObject("{\"foodname\": [\"rice\"], \"calorie\": [\"310\"]}");
            System.out.println("\n\n" + jsonObject.getString("foodname"));

            JSONArray foodName = jsonObject.getJSONArray("foodname");
            JSONArray calorie = jsonObject.getJSONArray("calorie");

            dto.setFoodName((List<String>) foodName);
            dto.setCalorie((List<String>) calorie);

        } catch (JSONException e) {
            System.out.println("Can't parse Data : " + response.getBody());
        }

        return dto;
    }

}
