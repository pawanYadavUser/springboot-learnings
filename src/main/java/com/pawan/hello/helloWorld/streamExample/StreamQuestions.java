package com.pawan.hello.helloWorld.streamExample;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



/*
* reverse the string using stringBuilder!
* reverse the string using reduce operator!
* find the longest word from the given sentence using max method in streams
* find the longest word from the given sentence using reduce operator
* find the list of words with max length!
* Sum of squares of even number! - using map, using reduce
* Concatenate the unique characters from the given string using reduce operator
*
* */

class User1 {
    int id;
    String name;

    public User1(int id, String name){
        this.id =id;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public String toString(){
        return this.id+" "+this.name;
    }
}

public class StreamQuestions {
    public static void main(String[] args) {
        String input = "This is Pawan";
        //Reverse the string!
        String result = Arrays.stream(input.split(" ")).
                map(word -> new StringBuilder(word).reverse().toString()).
                map(word -> "[" + word + "]").collect(Collectors.joining(" "));

        System.out.println("result1 :- "+result);


        String result2 = Arrays.stream(input.split(" ")).
                map(word -> word.chars().
                        mapToObj(c -> String.valueOf((char) c)).
                        reduce("", (rev, ch) -> ch + rev)
                ).
                map(str -> "[" + str + "]").collect(Collectors.joining(" "));

        String result3 = Arrays.stream(input.split(" ")).
                map(word -> word.chars()
                        .mapToObj(c -> String.valueOf((char) c))
                        .reduce("",(ch, rev)-> rev+ch)
                ).map(str -> "["+str+"]").collect(Collectors.joining(" "));

        System.out.println("result3 : "+result3);



//        🔴 Problem
//        word.chars() → produces an IntStream
//                .mapToObj(c -> (char) c) → now you have a Stream<Character>
//        But in reduce("", (rev, ch) -> ch + rev), your identity is a String, while ch is a Character
//        So the types don’t match → Java expects accumulator (String, Character) -> String, but it’s actually (String, Object) -> String.
//                The compiler will complain or auto-box incorrectly.

//🔑 Key point:
//        chars() → gives IntStream.
//        Convert to String (String.valueOf((char) c)) if you want to concatenate with String in reduce.
//✅ Pros:
//        Works directly with a reduction idea (accumulation → single result).
//        Simple for mathematical/string folding operations (sum, product, min, max, reverse).
//❌ Cons:
//        String concatenation with + inside reduce is inefficient (O(n^2) in worst case).
//        Not the most idiomatic for string building.

        System.out.println(result2);


        System.out.println();
        //find the longest word in the sentence.
        String input2 = "reduce helps in functional style programming";

        String longestWordInSentence = Arrays.stream(input2.split(" "))
                .max(Comparator.comparingInt(String::length)).orElse("");

        String longestWordInSentence2 = Arrays.stream(input2.split(" ")).reduce((w1,w2)-> (w1.length()>=w2.length() ? w1:w2 )).orElse("");

        System.out.println("Longest word in sentence : " + longestWordInSentence);
        System.out.println("Longest word in sentence using reduce method : " + longestWordInSentence2);

        //find the longest word without punctuation , splitting the string based on some non-word char (chars except a-zA-Z_)
        String longestWithoutPunctuation = Arrays.stream("Java, streams are powerful!".split("\\W+"))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("longest word without punctuation : "+longestWithoutPunctuation);

        //Find ALL Longest Words (Tie Case)
        int maxLength =  Arrays.stream("Java has a powerful stream function".split(" "))
                .mapToInt(String::length)
                .max().orElse(0);

        //mapToInt() converts a Stream<T> into an IntStream by extracting an int value from each element
//        In short:
//        Object stream → primitive int stream
//        Avoids boxing / unboxing
//        Improves performance
//        🔹 Common Methods Available After mapToInt()
//        Once you have an IntStream, you can do:
//                .max()
//                .min()
//                .sum()
//                .average()
//                .count()

        List<String> wordsWithLongestLength = Arrays.asList("Java has a powerful stream function".split(" "))
                .stream()
                .filter(ele -> ele.length() == maxLength)
                .collect(Collectors.toList());

        System.out.println("List of words of maximum length : "+wordsWithLongestLength.toString());

        Map.Entry<String, Integer> longestWordsWithLengths =
                Arrays.stream("Java has a powerful stream function".split(" "))
                        .collect(Collectors.toMap(
                                w -> w,
                                String::length,
                                (a, b) -> a))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(Map.entry("", 0));
        System.out.println(longestWordsWithLengths);
        //cleaner alternative
        Map.Entry<String, Integer> longestWordWithLengthCleaner = Arrays.stream("Java has a powerful stream function".split(" "))
                        .map(element -> Map.entry(element, element.length()))
                                .max(Map.Entry.comparingByValue())
                                        .orElse(Map.entry("",0));
        System.out.println("Longest word with length cleaner approach : "+longestWordWithLengthCleaner);
        System.out.println();





        //Sum of squares of even numbers
        int [] input3 = {1, 2, 3, 4, 5, 6};
        int sumOfSquaresOfEven = Arrays.stream(input3).filter(ele -> (ele%2 == 0)).map(n->n*n).sum();
        int sumOfSquaresOfEven2 = Arrays.stream(input3).filter(ele->ele%2==0).map(n->n*n).reduce(0,(a,b)-> a+b);
//        🔹 Variant 3 — Without map, do square inside reduce
        int sumOfSquaresOfEven3 = Arrays.stream(input3).filter(ele->ele%2==0).reduce(0,(a,b)->a+b*b);

        System.out.println("Sum of squares of even numbers : "+sumOfSquaresOfEven);
        System.out.println("Sum of squares of even numbers using reduce : "+sumOfSquaresOfEven2);
        System.out.println("Sum of squares of even numbers using reduce compact variant : "+sumOfSquaresOfEven3);

        //🔹 3. Concatenate only unique characters from a string
        //Input:"banana"
        String input4 = "banana";
        String uniqueCharString = IntStream.range(0, input4.length())
                .mapToObj(i-> String.valueOf(input4.charAt(i)))
                        .reduce("",(res, cur) -> (!res.contains(cur)) ? res+cur : res);

        //parallel safe version
        Set<String> uniqueCharString2 = IntStream.range(0,input4.length())
                        .mapToObj(i->String.valueOf(input4.charAt(i)))
                        .reduce(
                                new LinkedHashSet<String>(),
                                (res, cur)->{
                                    res.add(cur);
                                    return res;
                                },
                                (set1, set2)->{
                                    set1.addAll(set2);
                                    return set1;
                                }
                        );

        System.out.println("uniqueCharString : "+uniqueCharString);
        System.out.println("uniqueCharString parallel thread safe version  : "+String.join("",uniqueCharString2));

        // filter even numbers from the list
        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6);
        System.out.println("filtered even numbers for list : "+list1.toString() + " | "+list1.stream().filter(e->e%2==0).toList());

        //Convert list of strings to uppercase
        List<String> names = Arrays.asList("pawan", "java", "stream");
        System.out.println("uppercase strings from list : "+names.toString() + " | "+names.stream().map(String::toUpperCase).toList());

        // Find the sum of all numbers
        List<Integer> list2 = Arrays.asList(1,2,3,4,5);
        System.out.println("Sum for given list : "+list2.toString() + " | "+list2.stream().reduce(Integer::sum).orElse(0));

       //Count how many strings have length greater than 3.
        List<String> names1 = Arrays.asList("Ram", "Shyam", "Mohan", "Amit");
        System.out.println("Count of strings having length greater than 3 from list : "+names1  + " | "+names1.stream().filter(e->e.length()>3).count());

        //Medium level questions!

//        ✅ 2. Group strings by their length
        List<String> list3 = Arrays.asList("java", "is", "fun", "cool");
//        Map<Integer, Set<String>> result1 = list3.stream().collect(Collectors.groupingBy(String::length,Collectors.toSet()));
//        💡 Say this in interview:“Using Set removes duplicates, so I’d confirm requirement before choosing Set vs List.”
        Map<Integer, List<String>> result1 =
                list3.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("Group strings by their length from list : "+list3+ " | "+result1);

//        ✅ 3. Find the second highest number
        List<Integer> list4 = Arrays.asList(10, 20, 30, 40, 50, 7, 8, 2, 3, 4, 67, 11, 51);
//        List<Integer> secondHighestNumber = list4.stream().sorted().distinct().skip(list4.size()-2).toList();
//        ❌ Problem here
//        👉 Issue:
//        You used list4.size() → original list size
//        After distinct(), size changes → wrong result possible
        int secondHighest = list4.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow();
        System.out.println("second highes number from list : "+ list4 + " | "+secondHighest);
//        ✔ Cleaner
//        ✔ Works even with duplicates
//        ✔ No dependency on original size
//        💡 Interview gold line:
//        “Avoid using original size after stream transformations.”


//        ✅ 4. Convert list of objects into a Map
        List<User1> user1List = new ArrayList<>();
        user1List.add(new User1(1,"Ray"));
        user1List.add(new User1(1,"Kai"));
        user1List.add(new User1(2,"Vanick"));
        Map<Integer, String> resultMap = user1List.stream().collect(Collectors.toMap(User1::getId,User1::getName,(a,b)->b));
        System.out.println("Convert list : " + user1List + "to map object | "+resultMap.toString());

//        ✅ 5. Find the longest string
        List<String> list5 = Arrays.asList("java", "stream", "api", "programming");
        System.out.println("Longest string from list : "+list5 + " | "+list5.stream().max(Comparator.comparingInt(String::length)).orElse(""));
    }
}
