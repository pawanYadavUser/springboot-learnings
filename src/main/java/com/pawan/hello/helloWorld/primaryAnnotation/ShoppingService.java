package com.pawan.hello.helloWorld.primaryAnnotation;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ShoppingService {

    private final PaymentService paymentService;

    // Constructor injection
    @Autowired
    public ShoppingService( @Qualifier("CreditCardPayment") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void checkout() {
        paymentService.pay();
    }

    @PostConstruct
    public void init() {
        System.out.println("Shopping service bean created by Spring!");
    }
}