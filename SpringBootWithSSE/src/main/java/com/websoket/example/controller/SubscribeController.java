package com.websoket.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubscribeController {
    @GetMapping("/sub")
    public String test(){
        return "index";
    }
}
