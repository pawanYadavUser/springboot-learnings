package com.pawan.hello.helloWorld.ConcurrentEnvironment;

import java.util.HashMap;
import java.util.Map;

public class HashMapConcurrentDemo {
    public static void main(String[] args) throws InterruptedException{
        Map<Integer, String> map = new HashMap<>();

        Runnable task = ()->{
          for(int i=0; i<1000;i++){
              map.put(i, "Value"+i);
          }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Size of HashMap: " + map.size());

//        Expected output : Size of HashMap: 1000
//        Actual output : Size of HashMap: 800, 950, 1000, 870 ... (varies randomly!)
//        👉 Because both threads are writing at the same time, the HashMap structure gets corrupted,
//        causing lost updates. In extreme cases, it can even go into an infinite loop (in Java 7 with rehashing).
    }
}
