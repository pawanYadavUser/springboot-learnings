package com.pawan.hello.helloWorld.BeanLifecycleMethodsExample;

// File: src/main/java/com/example/lifecycle/HelperBean.java
//package com.example.lifecycle;

import org.springframework.stereotype.Component;

@Component
public class HelperBean {
    public HelperBean() {
        System.out.println("2) Constructor: HelperBean() called");
    }
}
