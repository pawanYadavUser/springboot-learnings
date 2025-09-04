package com.pawan.hello.helloWorld.OverridingRunTImePolymorphism;

class A {
    void display() {
        System.out.println("Display from A");
    }
}

class B extends A {
    // Overriding (same signature, new implementation)
    @Override
    void display() {
        System.out.println("Display from B");
    }
}

public class Main {
    public static void main(String[] args) {
        A obj1 = new A();
        obj1.display();  // Calls A's method

        B obj2 = new B();
        obj2.display();  // Calls B's method

        A obj3 = new B(); // Reference of A, object of B
        obj3.display();   // Calls B's method (runtime polymorphism)
    }
}

