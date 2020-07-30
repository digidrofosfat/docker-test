package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("greeting")
public class TestController {

    @GetMapping
    public String get() {
        return "dasdasd";
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
