package com.pawan.hello.helloWorld;

import com.pawan.hello.helloWorld.Service.OrderServiceSetter;
import com.pawan.hello.helloWorld.Service.PaymentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceSetterTest {

    @Test
    void shouldPlaceOrder() {
        PaymentService mockPayment = () -> "mock payment";

        OrderServiceSetter service = new OrderServiceSetter();
        service.setPaymentService(mockPayment);

        assertEquals("mock payment", service.placeOrder());
    }

    @Test
    void shouldFailIfNotInjected() {

        /**
         * uncomment the part for failing the test case
         */

//        PaymentService mockPayment = () -> "mock payment";

        OrderServiceSetter service = new OrderServiceSetter();
//        service.setPaymentService(mockPayment);

        assertThrows(NullPointerException.class, service::placeOrder);
    }
}
