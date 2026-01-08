package com.pawan.hello.helloWorld.Service;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceConstructor {

    private final PaymentService paymentService;


    public OrderServiceConstructor(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    public String placeOrder(){
        return paymentService.pay();
    }

}
