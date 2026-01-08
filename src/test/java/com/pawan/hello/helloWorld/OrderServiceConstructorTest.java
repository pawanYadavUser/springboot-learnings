package com.pawan.hello.helloWorld;

import com.pawan.hello.helloWorld.Service.OrderServiceConstructor;
import com.pawan.hello.helloWorld.Service.PaymentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceConstructorTest {

    @Test
    void shouldPlaceOrder(){
        //fake dependency
        PaymentService mockPayment = () -> "mock payment";

        //create service manually (No Spring)
        OrderServiceConstructor service = new OrderServiceConstructor(mockPayment);

        //verify behaviour
        assertEquals("mock payment",mockPayment.pay());
    }
}
