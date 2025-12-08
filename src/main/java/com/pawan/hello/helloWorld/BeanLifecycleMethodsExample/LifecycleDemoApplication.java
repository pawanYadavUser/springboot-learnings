package com.pawan.hello.helloWorld.BeanLifecycleMethodsExample;

// File: src/main/java/com/example/lifecycle/LifecycleDemoApplication.java
//package com.example.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LifecycleDemoApplication implements CommandLineRunner {

    @Autowired
    private MyBean myBean;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(LifecycleDemoApplication.class, args);
        // Close context after a short sleep so destruction hooks run and you can see output
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        ctx.close();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>> Application started, using myBean: " + myBean);
    }
}
