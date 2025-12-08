package com.pawan.hello.helloWorld.BeanLifecycleMethodsExample;

// File: src/main/java/com/example/lifecycle/ConfigBeans.java
//package com.example.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBeans {

    // Example of a bean created via @Bean with custom init/destroy methods
    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public MyBean myBeanWithCustomInit() {
        // Note: we return a fresh MyBean here — but in this demo we already have @Component MyBean.
        // This is just to show how initMethod/destroyMethod is declared for @Bean-based beans.
        return new MyBean("From@Bean");
    }
}
