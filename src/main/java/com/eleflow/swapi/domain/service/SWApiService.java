package com.eleflow.swapi.domain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SWApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String uri = "https://swapi.dev/api/planets";



    public String get() {
        ResponseEntity<String> response = restTemplate.getForEntity(uri + "/1", String.class);
        System.out.println("\n\n\n");
        System.out.println("\n\n\n" + response.getBody());
        return response.getBody();
    }
}
