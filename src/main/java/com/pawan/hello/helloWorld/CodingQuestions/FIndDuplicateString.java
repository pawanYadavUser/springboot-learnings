package com.pawan.hello.helloWorld.CodingQuestions;

import java.util.*;
import java.util.stream.Collectors;

public class FIndDuplicateString {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "apple", "banana", "orange", "apple", "mango", "banana", "grapes"
        );

        Set<String> duplicates = names.stream()
                .collect(Collectors.groupingBy(s->s, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e->e.getValue()>1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        Set<String> duplicates1 = names.stream().filter(name -> Collections.frequency(names,name)>1)
                        .collect(Collectors.toSet());

        Set<String> seen = new HashSet<>();
        Set<String> duplicates2 = names.stream().filter(name -> !seen.add(name)).collect(Collectors.toSet());

        System.out.println(duplicates);
        System.out.println(duplicates1);
        System.out.println(duplicates2);
    }
}
