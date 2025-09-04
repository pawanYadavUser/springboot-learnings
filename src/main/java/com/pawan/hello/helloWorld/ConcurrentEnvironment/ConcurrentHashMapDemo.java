package com.pawan.hello.helloWorld.ConcurrentEnvironment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> map = new ConcurrentHashMap<>(); // Thread-safe

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, "Value" + i);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Size of ConcurrentHashMap: " + map.size());
    }
}

//👉 Because ConcurrentHashMap uses fine-grained locking (bucket-level + CAS operations),
// it ensures no corruption or data loss in concurrent environments.