package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@RestController
public class CountyController {

    private final String BASE_URL = "https://ws.geonorge.no/kommuneinfo/v1/fylker/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/county/{countyNumber}")
    public String getCountyName(@PathVariable("countyNumber") String countyNumber) {
        String url = BASE_URL + countyNumber;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                return "Feil under henting av fylke.";
            }
        } catch (Exception e) {
            return "Feil under henting av fylke: " + e.getMessage();
        }
    }
}
