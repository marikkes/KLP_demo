package com.example.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/county")
public class CountyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{countyNumber}")
    public ResponseEntity<String> getCountyName(@PathVariable String countyNumber) {
        String url = "https://ws.geonorge.no/kommuneinfo/v1/fylker/" + countyNumber;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());
            String countyName = root.path("fylkesnavn").asText();

            return ResponseEntity.ok(countyName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Feil under henting av fylkesnavn");
        }
    }
}
