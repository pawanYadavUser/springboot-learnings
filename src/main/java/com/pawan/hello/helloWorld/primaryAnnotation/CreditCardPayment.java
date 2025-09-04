package com.pawan.hello.helloWorld.primaryAnnotation;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component("CreditCardPayment")
public class CreditCardPayment implements  PaymentService{
    public void pay(){
        System.out.println("Payment via credit card");
    }
    @PostConstruct
    public void init() {
        System.out.println("CreditCardPayment service bean created by Spring!");
    }

}
