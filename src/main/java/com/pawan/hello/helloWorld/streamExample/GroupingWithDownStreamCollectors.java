package com.pawan.hello.helloWorld.streamExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingWithDownStreamCollectors {
    public static void main(String[] args) {
//        Count how many words per length
        List<String> words = Arrays.asList("apple", "bat", "ball", "cat", "elephant");
//        {3=2, 4=1, 5=1, 8=1}
        Map<Integer, Long> grouped1 = words.stream()
                .collect(Collectors.groupingBy(
                        String::length,         // classifier
                        Collectors.counting()) // downstream collector
                );

        System.out.println(grouped1);

//        Group employees by department and count
//        {HR=2, IT=2, Finance=1}
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "HR"),
                new Employee("Bob", "IT"),
                new Employee("Charlie", "IT"),
                new Employee("Diana", "Finance"),
                new Employee("Eve", "HR")
        );

        Map<String, Long> grouped2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(grouped2);

//        Group numbers by even/odd and sum them
//        {Odd=9, Even=12}
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Map<String, Integer> grouped3 = numbers.stream()
                .collect(Collectors.groupingBy(
                        n->(n%2)==0 ? "even":"odd",     // classifier
                        Collectors.summingInt(n -> n)   // downstream sum
                ));
        System.out.println(grouped3);

//        Group words by first letter but only keep their lengths
//        {a=[5, 3], b=[6, 4], c=[6]}
        List<String> wordList = Arrays.asList("apple", "ant","banana", "ball" ,"carrot");
        Map<Character, List<Integer>> grouped4 = wordList.stream()
                .collect(
                        Collectors.groupingBy(
                                str -> str.charAt(0),
                                Collectors.mapping(String::length,
                                        Collectors.toList())
                        )
                );
        System.out.println(grouped4);
    }
}
