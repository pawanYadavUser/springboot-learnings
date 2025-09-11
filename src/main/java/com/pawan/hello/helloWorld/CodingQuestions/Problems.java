package com.pawan.hello.helloWorld.CodingQuestions;

import java.util.*;
import java.util.stream.Collectors;

public class Problems {
    public static void main(String[] args) {
        int [] arr = {1,3,5,2,7,4,2,4,5,6,9};

//        find the  3 unique number from the array!

//        for(int i=0; i<arr.length; i++){
//
//
//        }

        List<Integer> result = Arrays.stream(arr).distinct()
                .mapToObj(ele -> Integer.valueOf(ele))
                .sorted()
        .collect(Collectors.toList());



        System.out.println(result);


        Map<Integer, Integer> resultMap = Arrays.stream(arr).collect(Collectors.groupingBy(s->s,Collectors.counting()));

    }
}
