package com.pawan.hello.helloWorld.Service;

public class OrderServiceSetter {

    private PaymentService paymentService;

    public OrderServiceSetter() {}

    // optional dependency
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String placeOrder() {
        return paymentService.pay(); // potential NPE
    }
}
