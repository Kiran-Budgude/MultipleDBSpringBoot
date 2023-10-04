package com.example.two.db.demo.service;

import com.example.two.db.demo.entity.db2.FirebaseDynamicLinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class DynamicLinkService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${firebase.dynamicLinksApiUrl}")
    private String dynamicLinksApiUrl;



    public DynamicLinkService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String generateDynamicLink(String longLink) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("dynamicLinkInfo", new HashMap<>());
        requestMap.put("longDynamicLink", longLink);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestMap, headers);

        FirebaseDynamicLinkResponse response = restTemplate.postForObject(dynamicLinksApiUrl, request, FirebaseDynamicLinkResponse.class);

        if (response != null) {
            return response.getShortLink();
        } else {
            throw new RuntimeException("Failed to generate dynamic link");
        }
    }
}

