package com.pawan.hello.helloWorld.ComparableVsComparator;

import java.util.*;

// Student implements Comparable (Natural order = by id)
class Student implements Comparable<Student> {
    int id;
    String name;
    int marks;

    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Comparable (natural ordering: by id)
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id); // sorting by id
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + marks;
    }
}

// Comparator for sorting by name
class NameComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}

// Comparator for sorting by marks
class MarksComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.marks, s2.marks);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(3, "Ravi", 85));
        list.add(new Student(1, "Amit", 92));
        list.add(new Student(2, "Neha", 78));

        // 1. Sort using Comparable (Natural Order = by id)
        Collections.sort(list);
        System.out.println("Sorted by ID (Comparable): " + list);

        // 2. Sort using Comparator (by name)
        Collections.sort(list, new NameComparator());
        System.out.println("Sorted by Name (Comparator): " + list);

        // 3. Sort using Comparator (by marks)
        Collections.sort(list, new MarksComparator());
        System.out.println("Sorted by Marks (Comparator): " + list);
    }
}
