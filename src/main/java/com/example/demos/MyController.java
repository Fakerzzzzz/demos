package com.example.demos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello"; // This matches the name 'hello.jsp'
    }
}