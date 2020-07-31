package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("greeting")
public class TestController {

    @GetMapping
    public String get() {

        RestTemplate restTemplate = new RestTemplate();
return restTemplate.getForObject("https://httpbin.org/ip", String.class);

    }

    @GetMapping("/listHeaders")
    public String listAllHeaders(
            @RequestHeader Map<String, String> headers) {
        StringBuilder result = new StringBuilder();
        headers.forEach((key, value) -> {
            result.append(String.format("Header '%s' = %s", key, value));
            result.append("<br />");
        });

        return result.toString();
    }
}
