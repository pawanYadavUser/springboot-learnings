package com.pawan.hello.helloWorld.Stack;

import java.util.ArrayDeque;
import java.util.Stack;

class Node {
    int data;
    Node next;
    Node(int d) { data = d; }
}

class StackLL {
    Node top;

    void push(int x) {
        Node node = new Node(x);
        node.next = top;
        top = node;
    }

    int pop() {
        if (top == null) throw new RuntimeException("Underflow");
        int val = top.data;
        top = top.next;
        return val;
    }

    void display(){
        Node temp = top;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        StackLL stackLL = new StackLL();

        stackLL.push(2);
        stackLL.push(3);
        stackLL.push(5);

        stackLL.display();

        stackLL.pop();
        stackLL.pop();

        stackLL.display();

        //java built-in implementation
        Stack<Integer> stack = new Stack<>();

        // Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Display stack
        System.out.println("Stack: " + stack);

        // Peek
        System.out.println("Peek: " + stack.peek()); // 30

        // Pop
        System.out.println("Pop: " + stack.pop()); // 30

        // Display again
        System.out.println("Stack after pop: " + stack);

        ArrayDeque<Integer> stackADQ = new ArrayDeque<>();

        // Push elements (use addFirst or push)
        stackADQ.push(10);
        stackADQ.push(20);
        stackADQ.push(30);

        // Display
        System.out.println("Stack: " + stackADQ);

        // Peek
        System.out.println("Peek: " + stackADQ.peek()); // 30

        // Pop
        System.out.println("Pop: " + stackADQ.pop()); // 30

        // Display again
        System.out.println("Stack after pop: " + stackADQ);


    }
}

