package com.pawan.hello.helloWorld.streamExample;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupWordsByLength {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Apple", "bat", "ball", "cat", "elephant");
        Map<Integer, Long> grouped = words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));

        Map<Integer, List<String>> grouped2 = Arrays.asList("Apple", "bat", "ball", "cat", "elephant")
                        .stream().collect(Collectors.groupingBy(String::length));

        System.out.println(grouped);
        System.out.println(grouped2);

        //Group numbers as even or odd!
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);

        //{"even":[2,4,6,8], "odd":[1,3,5,7,9]}

        Map<String, List<Integer>> grouped3 = numbers.stream()
                .collect(Collectors.groupingBy(n->(n%2)==0 ? "even":"odd"));

        System.out.println(grouped3);

//        Group numbers by their remainder when divided by 3
        Map<Integer, List<Integer>> grouped4 = Arrays.asList(5,7,8,9,10,11,12)
                .stream()
                .collect(Collectors.groupingBy(n->n%3));
        System.out.println(grouped4);

//        Groups word by their first character
        List<String> wordList = Arrays.asList("apple","bat","banana","ball","cat","carrot");
        Map<Character,List<String>> grouped5 = wordList.stream()
                .collect(Collectors.groupingBy(s->s.charAt(0)));
        System.out.println(grouped5);

    }
}
