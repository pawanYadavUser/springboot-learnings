package com.pawan.hello.helloWorld.BeanLifecycleMethodsExample;

// File: src/main/java/com/example/lifecycle/MyBean.java
//package com.example.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MyBean implements InitializingBean, DisposableBean {

    private final String requiredValue;

    @Autowired
    private HelperBean helper;   // field injection example (could be constructor)

    public MyBean(@Value("${app.name:LifecycleApp}") String requiredValue) {
        System.out.println("1) Constructor: MyBean() called"+requiredValue);
        this.requiredValue = requiredValue;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("4) @PostConstruct: postConstruct() called");
        System.out.println("   -> injected helper is " + (helper != null ? "available" : "null"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("5) afterPropertiesSet(): InitializingBean callback");
    }

    // Custom init-method example — declared on a @Bean below (for illustration)
    public void customInit() {
        System.out.println("6) custom init-method invoked");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("9) DisposableBean.destroy() called");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("8) @PreDestroy: preDestroy() called");
    }

    // Custom destroy-method (if registered in @Bean, not used for component)
    public void customDestroy() {
        System.out.println("10) custom destroy-method invoked");
    }

    @Override
    public String toString() {
        return "MyBean{appName='" + requiredValue + "'}";
    }
}
