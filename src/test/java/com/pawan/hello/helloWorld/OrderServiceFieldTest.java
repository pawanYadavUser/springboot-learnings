package com.pawan.hello.helloWorld;

import com.pawan.hello.helloWorld.Service.OrderServiceField;
import com.pawan.hello.helloWorld.Service.PaymentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceFieldTest {

    @Test
    void cannotTestWithoutSpring(){
        OrderServiceField service = new OrderServiceField();

        //paymentService is null -> NPE
        assertThrows(NullPointerException.class,service::placeOrder);

//        PaymentService paymentService = new PaymentService() {
//            @Override
//            public String pay() {
//                return "mock payment via field injection";
//            }
//        };
//        OrderServiceField service2 = new OrderServiceField();
//        service2.
//        assertEquals("mock payment via field injection",)
//        Why u stopped here , becoz, except spring's application context there is no way , possible
//        that you can let sevice2 know about the bean from paymentService in fieldInjection method
    }
}
