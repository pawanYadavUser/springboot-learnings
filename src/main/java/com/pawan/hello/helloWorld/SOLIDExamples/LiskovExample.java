package com.pawan.hello.helloWorld.SOLIDExamples;


import com.pawan.hello.helloWorld.primaryAnnotation.PaymentService;



class Payment {
    public void pay(int amount){
        System.out.println("paid : "+amount+" using generic method. ");
    }
}

class CODPayment extends Payment{
    @Override
    public void pay(int amount){
        if(amount>5000){
            throw new RuntimeException("COD not allowed for higher amounts. ");

        }
        System.out.println("Paid : "+amount+" via COD option. ");
    }
}
public class LiskovExample {
    public void processPayment(Payment payment){
        payment.pay(4000);

    }

    public static void main(String[] args) {
        LiskovExample service = new LiskovExample();
        Payment payment = new CODPayment();//substitution
        service.processPayment(payment);
    }
}

//1. Code breaks when subclass is substituted
//
//payment.pay(8000) → causes a RuntimeException
//But the base class never defined such a restriction.
//
//        2. Unexpected behavior
//
//The code expects payment to always succeed (as per parent class behavior),
//but CODPayment introduces a new rule → unexpected failure.
//
//        3. Violations of polymorphism
//Payment p = new CODPayment();
//p.pay(8000);   // should behave like Payment's contract
//But subclass changes the rules → breaks polymorphism.