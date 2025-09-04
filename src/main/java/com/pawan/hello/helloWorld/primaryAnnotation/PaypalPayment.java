package com.pawan.hello.helloWorld.primaryAnnotation;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PaypalPayment implements PaymentService{
    public void pay(){
        System.out.println("Payment via Paypal");
    }

    @PostConstruct
    public void init() {
        System.out.println("PaypalPayment service bean created by Spring!");
    }
}
