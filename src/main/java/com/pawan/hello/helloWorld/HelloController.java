package com.pawan.hello.helloWorld;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld(){
        return "Hello, world !!!!!!!!!!!!!!";
    }

    @PostConstruct
    public void init() {
        System.out.println("Hello Controller bean created by Spring!");
    }

}
