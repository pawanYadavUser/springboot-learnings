package com.pawan.hello.helloWorld.BeanLifecycleMethodsExample;

// File: src/main/java/com/example/lifecycle/LoggingBeanPostProcessor.java
//package com.example.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class LoggingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean.getClass().getPackage().getName().startsWith("com.pawan")) {
            System.out.println("Before Init: " + beanName);
        }
        return bean;
    }

//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("3.5) BeanPostProcessor.postProcessBeforeInitialization(): " + beanName);
//        return bean; // can return proxy instead
//    }

//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("7) BeanPostProcessor.postProcessAfterInitialization(): " + beanName);
//        return bean;
//    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException{
        if (bean.getClass().getPackage().getName().startsWith("com.pawan")) {
            System.out.println("7) BeanPostProcessor.postProcessAfterInitialization(): " + beanName);
        }
        return bean;
    }

}
