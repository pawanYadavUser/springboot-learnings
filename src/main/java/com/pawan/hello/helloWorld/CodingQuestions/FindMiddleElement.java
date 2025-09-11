package com.pawan.hello.helloWorld.CodingQuestions;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class FindMiddleElement {
    // Function to find middle of linked list
    public static Node findMiddle(Node head) {
        if (head == null) return null;

        Node slow = head;
        Node fast = head;

        // stop one step earlier for even case
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

//        while (fast != null && fast.next != null) {
//            slow = slow.next;       // move 1 step
//            fast = fast.next.next;  // move 2 steps
//        }

        return slow; // slow is at middle
    }

    public static void main(String[] args) {
        // create linked list: 1 -> 2 -> 3 -> 4 -> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
//        head.next.next.next.next = new Node(5);

        Node middle = findMiddle(head);

        System.out.println("Middle element is: " + middle.data);
    }
}