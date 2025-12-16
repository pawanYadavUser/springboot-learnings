package com.pawan.hello.helloWorld.streamExample;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        //find the longest word without punctuation
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
    }
}
