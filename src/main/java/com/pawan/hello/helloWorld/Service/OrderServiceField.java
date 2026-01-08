package com.pawan.hello.helloWorld.Service;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceField {
    @Autowired
    private PaymentService paymentService;

    public String placeOrder() {
        return paymentService.pay();
    }
}
