package com.pawan.hello.helloWorld.CME;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CMEEXample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

//        for (String s : list) {
//            if (s.equals("B")) {
//                list.remove(s); // ❌ Structural modification while iterating
//            }
//        }

        //avoid CME
        Iterator<String> it = list.iterator();
        while(it.hasNext()) {
            String s = it.next();
            if (s.equals("B")) {
                it.remove(); // safe way!
            }
        }

        list.stream().forEach(System.out::println);

        //avoid CME
        CopyOnWriteArrayList <String> list2 = new CopyOnWriteArrayList<>();
        list2.add("A");
        list2.add("B");
        list2.add("C");
        list2.add("D");

        for(String s : list2){
            if(s.equals("B")){
                list2.remove(s);
            }
        }

        list2.stream().forEach(System.out::println);

    }
}
