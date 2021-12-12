package com.knu.dibly.api;

import com.knu.dibly.api.dto.FlaskResponseDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
//        System.out.println("\nContent-Type : " + response.getHeaders().getContentType());
//        System.out.println("Body : " + response.getBody());
//        System.out.println("Body Type : " + response.getBody().getClass() + "\n\n");

        // Response 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        FlaskResponseDto dto = objectMapper.readValue(response.getBody(), FlaskResponseDto.class);

        return dto;
    }

}
