package com.pawan.hello.helloWorld;


/*
Let us consider a class of 20 students with the following scores in a given Subject:
98, 100, 35, 75, 73, 81, 86, 75, 100, 75, 35, 75, 17, 98, 100, 35, 75, 73, 81, 86

As a class teacher, I would like to print:
- The second top score : [secondTopScore]
- The number of students scoring second top score : [count]
*/

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Student {
    private int marks;

    public Student(int marks){
        this.marks = marks;
    }

    public int getMarks(){
        return this.marks;
    }
}

public class Test {
    public static void main(String[] args) {

        List<Student> classOfStudents = new ArrayList<>();

        int [] marks = new int[]{98, 100, 35, 75, 73, 81, 86, 75, 100, 75, 35, 75, 17, 98, 100, 35, 75, 73, 81, 86, 125};

        for(int marksEach : marks){
            classOfStudents.add(new Student(marksEach));
        }

        List<Integer> sortedMarksAsc = classOfStudents.stream().map(student-> student.getMarks()).sorted().distinct().collect(Collectors.toList());

        System.out.println("The second top score : "+sortedMarksAsc.get(sortedMarksAsc.size()-2));

        System.out.println("The number of students scoring second top score : " +
                classOfStudents.stream().filter(student -> (student.getMarks() == sortedMarksAsc.get(sortedMarksAsc.size()-2))).count());

    }
}

