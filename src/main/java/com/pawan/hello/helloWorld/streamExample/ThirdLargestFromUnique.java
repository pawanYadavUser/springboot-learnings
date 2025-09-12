package com.pawan.hello.helloWorld.streamExample;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ThirdLargestFromUnique {
    public static void main(String[] args) {
        int [] arr = {1,3,5,2,7,4,2,4,5,6,9};

        Integer thirdLargest = Arrays.stream(arr)
                .boxed()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElse(null);

        System.out.println("Third largest from the unique numbers : "+thirdLargest);

//        List<Integer> thirdLargestVariant2 = Arrays.stream(arr)
//                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
//                .entrySet().stream()
//                .filter(entry -> entry.getValue() == 1 )
//                .map(Map.entry:: getKey)
//                .collect(Collectors.toList());

        List<Integer> uniqueOnly = Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy(s->s, Collectors.counting())) // count frequencies
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)  // keep only elements that appear once
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(uniqueOnly);


    }
}
