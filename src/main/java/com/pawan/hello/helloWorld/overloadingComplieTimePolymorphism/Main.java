package com.pawan.hello.helloWorld.overloadingComplieTimePolymorphism;

class A {
    void display() {
        System.out.println("Display from A");
    }
}

class B extends A {
    // Overloading (new method, different parameter)
    void display(String msg) {
        System.out.println("Display from B: " + msg);
    }
}

public class Main {
    public static void main(String[] args) {
        B obj = new B();
        obj.display();              // Calls A's method
        obj.display("Hello World"); // Calls B's overloaded method
    }
}
