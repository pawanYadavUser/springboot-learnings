package com.pawan.hello.helloWorld.ConcurrentEnvironment;

import java.util.HashMap;
import java.util.Map;

public class HashMapUse {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        //thread1
        new Thread(()->{
            map.put("A",1);
        }).start();

        //thread2
        new Thread(()->{
            map.put("A",2);
        }).start();

        //thread3
        new Thread(()->{
            System.out.println(map.get("A"));
        }).start();
    }
}
