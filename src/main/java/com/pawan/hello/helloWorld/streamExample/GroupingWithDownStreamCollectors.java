package com.pawan.hello.helloWorld.streamExample;

import java.util.*;
import java.util.stream.Collectors;


class Clients {
    String name;
    String dept;
    int salary;

    Clients(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }
}


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
        System.out.println("even & odd numbers sum: "+grouped3);

//        Group words by first letter but only keep their lengths
//        {a=[5, 3], b=[6, 4], c=[6]}
        List<String> wordList = Arrays.asList("apple", "ant", "banana", "ball" ,"carrot");
        Map<Character, List<Integer>> grouped4 = wordList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                str -> str.charAt(0),
                                Collectors.mapping(
                                        String::length,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("grouped4 : "+grouped4);

        //1️⃣: Group words by length, store first character only
        List<String> words2 = List.of("apple", "ant", "banana", "ball", "car");
        Map<Integer, List<Character>> result =
                words2.stream()
                        .collect(Collectors.groupingBy(
                                String::length,
                                Collectors.mapping(
                                        str -> str.charAt(0),
                                        Collectors.toList()
                                )
                        ));
        //👉 Grouping key = word length
        //👉 Stored value = first character
        System.out.println(result);

//        2️⃣: Group employees by department, store only names
        List<Clients> clients = List.of(
                new Clients("Amit", "IT", 60000),
                new Clients("Neha", "HR", 50000),
                new Clients("Raj", "IT", 70000)
        );
        Map<String, List<String>> result2 =
                clients.stream()
                        .collect(Collectors.groupingBy(
                                e -> e.dept,
                                Collectors.mapping(
                                        e -> e.name,
                                        Collectors.toList()
                                )
                        ));

        //👉 Without mapping, you’d get List<Employee>
        //👉 With mapping, you get only what you want
        System.out.println(result2);

//        3️⃣: Group numbers by even/odd, store their squares
        List<Integer> numbers2 = List.of(1, 2, 3, 4, 5);
        Map<String, List<Integer>> result3 =
                numbers2.stream()
                        .collect(Collectors.groupingBy(
                                n -> n % 2 == 0 ? "EVEN" : "ODD",
                                Collectors.mapping(
                                        n -> n * n,
                                        Collectors.toList()
                                )
                        ));
        System.out.println(result3);

//        Example 4️⃣: Collect to Set instead of List

        List<String> words3 = List.of("apple", "ant", "ape", "ball");

        Map<Character, Set<Integer>> result4 =
                words3.stream()
                        .collect(Collectors.groupingBy(
                                str -> str.charAt(0),
                                Collectors.mapping(
                                        String::length,
                                        Collectors.toSet()
                                )
                        ));
//        👉 mapping + toSet() removes duplicate lengths
        System.out.println(result4);

//        Example 6️⃣: Mapping + joining (very common)
        Map<Character, String> result5 =
                words3.stream()
                        .collect(Collectors.groupingBy(
                                str -> str.charAt(0),
                                Collectors.mapping(
                                        String::toUpperCase,
                                        Collectors.joining(", ")
                                )
                        ));
        System.out.println(result5);



    }
}
