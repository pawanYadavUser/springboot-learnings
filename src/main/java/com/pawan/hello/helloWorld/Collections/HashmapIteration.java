package com.pawan.hello.helloWorld.Collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashmapIteration {
    public static void main(String[] args) {
        Map<String,String> sampleMap = new HashMap<>();

        sampleMap.put("red","apple");
        sampleMap.put("yellow","banana");
        sampleMap.put("orange","orange");
        sampleMap.put("blue","berries");
        sampleMap.put("green","watermelon");

        System.out.println(sampleMap.entrySet());
        //Iterate over entry set
        for(Map.Entry<String, String> entry : sampleMap.entrySet() ){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        //Iterate over keys
        System.out.println("----------Iterate over keys--------------");
        for(String key : sampleMap.keySet()){
            System.out.println(key);
            System.out.println(key + " = " + sampleMap.get(key));
        }
        System.out.println("----------Iterate over values--------------");
        for(String value : sampleMap.values()){
            System.out.println(value);
        }

        System.out.println("---------------Using forEach method-------------");
        sampleMap.forEach((key, value)->{
            System.out.println(key + "=" + value);
        });

        System.out.println("-------Using iterator explicitly");
        Iterator <Map.Entry<String, String>> iterator = sampleMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            System.out.println(entry.getKey()+ " = "+entry.getValue());
        }

    }
}
