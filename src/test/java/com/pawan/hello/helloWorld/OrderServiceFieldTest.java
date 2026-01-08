package com.pawan.hello.helloWorld;

import com.pawan.hello.helloWorld.Service.OrderServiceField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceFieldTest {

    @Test
    void cannotTestWithoutSpring(){
        OrderServiceField service = new OrderServiceField();

        //paymentService is null -> NPE
        assertThrows(NullPointerException.class,service::placeOrder);
    }
}
