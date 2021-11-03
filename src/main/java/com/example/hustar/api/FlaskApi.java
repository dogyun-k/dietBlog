package com.example.hustar.api;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RequiredArgsConstructor
@Service
public class FlaskApi {

    public String getBase64String(MultipartFile multipartFile) throws Exception {
        byte[] bytes = multipartFile.getBytes();

        return Base64.getEncoder().encodeToString(bytes);
    }

    public String requestToFlask(String fileName, MultipartFile file) throws Exception {
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

        return response.getBody();
    }

}
