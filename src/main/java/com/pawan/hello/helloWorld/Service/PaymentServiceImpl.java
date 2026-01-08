package com.pawan.hello.helloWorld.Service;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Override
    public String pay() {
        return "real payment done";
    }
}
