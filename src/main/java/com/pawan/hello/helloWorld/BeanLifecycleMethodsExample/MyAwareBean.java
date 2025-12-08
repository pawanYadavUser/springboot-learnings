package com.pawan.hello.helloWorld.BeanLifecycleMethodsExample;

// File: src/main/java/com/example/lifecycle/MyAwareBean.java
//package com.example.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyAwareBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    public MyAwareBean() {
        System.out.println("3) Constructor: MyAwareBean() called");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("3.1) BeanNameAware.setBeanName(): beanName=" + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("3.2) BeanFactoryAware.setBeanFactory() called");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("3.3) ApplicationContextAware.setApplicationContext() called");
    }
}
