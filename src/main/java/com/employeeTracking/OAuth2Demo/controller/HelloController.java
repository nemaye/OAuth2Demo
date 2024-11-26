package com.employeeTracking.OAuth2Demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @GetMapping("/hellobrother")
    public String hello() {
        return "Hello, world!";
    }
}
