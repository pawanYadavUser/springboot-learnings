package com.pawan.hello.helloWorld;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld(){
        return "Hello, world !!!!!!!!!!!!!!";
    }

    @GetMapping("/controllerAnnotation/")
    @ResponseBody
    public String helloWorldFromControllerAnnotation( ){
        return "Hello world , from controller annotation";
    }

    @PostConstruct
    public void init() {
        System.out.println("Hello Controller bean created by Spring!");
    }

}
