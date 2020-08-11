package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("greeting")
public class TestController {

    @Autowired
    UfsProviderClientService service;
    @GetMapping
    public String get() {
        String result = service.getUserInfo("http://ufs-provider:8080/user/personal");
        if(result == null){
            throw new ForbiddenException();
        }
        return result;
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
