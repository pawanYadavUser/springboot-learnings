package com.pawan.hello.helloWorld.CodingQuestions;

public class FindDuplicatesInArray {
    public static void main(String[] args) {
        int [] arr = {4, 3, 2, 7, 8, 2, 3, 1};


        System.out.println("Duplicates are : ");

        for(int i=0;i<arr.length; i++){
            int index = Math.abs(arr[i])-1;

            if(arr[index]<0){
                //if its negative, then its a duplicate!
                System.out.print(Math.abs(arr[i])+" ");

            }else{
                //flip the sign
                //mark it as visited
                arr[index] = -arr[index];
            }

        }




    }


}
