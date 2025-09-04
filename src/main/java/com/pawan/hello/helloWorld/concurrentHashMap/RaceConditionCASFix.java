package com.pawan.hello.helloWorld.concurrentHashMap;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionCASFix {
    static int count =0;
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException{

        Runnable task = ()->{
          for( int i=0; i<1000; i++){
              count++;
              atomicInteger.incrementAndGet();//internally use CAS
          }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count (expected 2000): " + atomicInteger.get());

        System.out.println("Final count (expected 2000): " + count);
//How CAS fixes this
//
//When incrementAndGet() runs:
//
//It reads the value (say 1000).
//
//Tries compareAndSet(1000, 1001).
//
//If success → done.
//
//If fail (another thread already updated it), retry until success.
//
//This ensures no update is lost without locking the whole variable. 🚀


    }

}
