package com.example.hustar.api;

import com.example.hustar.domain.FlaskResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

public class FlaskApi {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    private String requestUrl = "http://localhost:5000/calorie";

    public ResponseEntity<FlaskResponseDto> requestToFlask(String filename, MultipartFile file) {
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<?> requestMessage = new HttpEntity<>("", httpHeaders);


//        ResponseEntity<FlaskResponseDto> response = restTemplate.postForObject(requestUrl, httpHeaders);
//        return response;
        return null;
    }

}
