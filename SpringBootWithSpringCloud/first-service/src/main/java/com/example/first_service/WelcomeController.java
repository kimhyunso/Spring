package com.example.first_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
public class WelcomeController {
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome first service application";
    }
}
