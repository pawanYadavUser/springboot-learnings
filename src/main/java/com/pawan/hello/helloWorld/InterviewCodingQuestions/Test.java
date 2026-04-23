package com.pawan.hello.helloWorld.InterviewCodingQuestions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

    public static String reverseString(String input){
//        char[] ch = input.toCharArray();
//
//        int left=0,right=input.length()-1;
//
//        while(left<=right){
//            char temp = ch[left];
//            ch[left] = ch[right];
//            ch[right] = temp;
//            left++;
//            right--;
//        }
//
//        return new String(ch);
        return Arrays.stream(input.split(" ")).map(word -> new StringBuilder(word).reverse().toString()).collect(Collectors.joining(" "));

    }

    public static boolean isPalindrome(String input){
//        char[] ch = input.toCharArray();
//        int left=0,right=input.length()-1;
//
//        while(left<=right){
//            if(ch[left] != ch[right]){
//                return false;
//            }
//            left++;
//            right--;
//        }
//        return true;
        //using int stream!
//        return IntStream.range(0,input.length()/2).allMatch(i-> input.charAt(i)==input.charAt(input.length()-1-i));

        //using stream + reverse
//        String reversed = input.chars().mapToObj(c->String.valueOf((char) c)).reduce("",(a,b)->b+a);
//        return input.equals(reversed);

        //optimized stream version
        //This is the 3-argument version of collect(), and it uses:👉 Supplier, Accumulator, Combiner
//        <R> R collect(Supplier<R> supplier,
//                BiConsumer<R, ? super T> accumulator,
//                BiConsumer<R, R> combiner)
//        ✅ 1. Supplier → “Give me a new empty container”
//        ✅ 2. Accumulator → “Add each element into container”
//        ✅ 3. Combiner → “Merge partial results” (for parallel streams)
//
//        👉 Used only if stream runs in parallel
//
//        Example (conceptual):
//
//        Thread 1 → "ma"
//        Thread 2 → "dam"
//
//        Combiner → "madam"
//
//⚠️ In your case (sequential stream), combiner is not really used, but Java still requires it.
//        🔥 Simple Analogy
//
//        Think of making chai ☕
//
//        Supplier → empty cup
//        Accumulator → adding milk, tea, sugar step by step
//        Combiner → if 2 people made half chai each → mix them
        String reversed = IntStream.range(0, input.length())
                .collect(StringBuilder::new,
                        (sb, i) -> sb.append(input.charAt(input.length() - 1 - i)),
                        StringBuilder::append)
                .toString();

        return input.equals(reversed);
    }

    public static int findDuplicateInArray(int [] input){
//        💡 Explanation
//        HashSet stores seen elements
//        First repeat = duplicate
//
//👉 Time: O(n) | Space: O(n)
        HashSet<Integer> set = new HashSet<>();
        for(int num : input){
            if(set.contains(num)) {
                //return first duplicate!
                return num;
            }
            set.add(num);
        }
        return -1;
    }


//💡 Explanation
//    Maintain pointer j for non-zero placement
//    Swap only when needed
//
//👉 Time: O(n) | Space: O(1)
    public static int[] moveZeroesToEnd(int[] arr){
        int j=0; //pointer to zero
        for(int i=0;i<arr.length;i++) {
            if (arr[i] != 0) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
        }
        return arr;
    }

//Anagram is a word created using letters of another word!
    public static boolean isAnagram(String s, String t){
        if(s.length() != t.length()) return false;

        int [] count = new int[26];

        for (int i=0; i<s.length(); i++){
            count[s.charAt(i)-'a']++;
            count[t.charAt(i)-'a']--;
        }
        for(int num : count){
            if(num!=0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "helloWorld";
         int[] arr1 = {1,2,2,3,4,5,6,7,8,9};
         int [] arr2 = {0,1,0,2,3,0,0,4,0,5,0,6,0,9,9};

        System.out.println("reversed string : "+reverseString(str));
        System.out.println("Is palindrome : "+str+" | "+isPalindrome(str));
        System.out.println("Is palindrome : "+"4994"+" | "+isPalindrome("4994"));

        System.out.println("Find duplicate in array: "+ Arrays.toString(arr1)+ " | "+findDuplicateInArray(arr1));

        System.out.println("Move zeros to end for arr : "+Arrays.toString(arr2) + " | "+Arrays.toString(moveZeroesToEnd(arr2)));

        System.out.println("Is anagram : "+"param  & ramap | " + isAnagram("param", "ramap"));
        System.out.println("Is anagram : "+"aman  & naman | " + isAnagram("aman", "naman"));

    }
}
