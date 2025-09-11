package com.pawan.hello.helloWorld.QueueLL;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Node {
    int data;
    Node next ;

    Node (int x){
        data = x;

    }
}
class QueueLL {
    Node front, rear;

    void enqueue(int x) {
        Node node = new Node(x);
        if (rear != null) rear.next = node;
        rear = node;
        if (front == null) front = node;
    }

    int dequeue() {
        if (front == null) throw new RuntimeException("Underflow");
        int val = front.data;
        front = front.next;
        if (front == null) rear = null;
        return val;
    }

    void display(){
        Node temp = front;
        if (temp!= null){
            while(temp != null){
                System.out.println(temp.data);
                temp = temp.next;
            }

        }else{
            System.out.println("empty queue");
        }
    }

    public static void main(String[] args) {
        QueueLL queueLL = new QueueLL();
        queueLL.enqueue(1);
        queueLL.enqueue(2);
        queueLL.enqueue(3);

        queueLL.display();

        queueLL.dequeue();

        queueLL.display();

        //LinkedList implemenation!
        Queue<Integer> q = new LinkedList<>();
        q.offer(10);
        q.offer(20);
        q.offer(30);

        System.out.println(q);      // [10, 20, 30]
        System.out.println(q.poll()); // 10
        System.out.println(q.peek()); // 20

        Queue<String> q2 = new ArrayDeque<>();
        q2.offer("A");
        q2.offer("B");
        q2.offer("C");

        System.out.println(q2);      // [A, B, C]
        System.out.println(q2.poll()); // A
        System.out.println(q2.peek()); // B

        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(30);
        pq.offer(10);
        pq.offer(20);

        System.out.println(pq.poll()); // 10 (lowest first)
        System.out.println(pq.poll()); // 20

        //concurrentLinkedQueue
        ConcurrentLinkedQueue<Integer> q3 = new ConcurrentLinkedQueue<>();
        q3.offer(10);
        q3.offer(20);
        q3.offer(30);

        System.out.println(q3.poll()); // 10
        System.out.println(q3.peek()); // 20

        try {
            BlockingQueue<Integer> q4 = new LinkedBlockingQueue<>(2);

            q4.put(1); // inserts
            q4.put(2); // inserts
//             q4.put(3); // would block if queue is full

            System.out.println(q4.take()); // 1
            System.out.println(q4.take()); // 2
        } catch (Exception exception){
            System.out.println("Error : "+ exception.getMessage());
        }

        try {
            BlockingQueue<String> q5 = new ArrayBlockingQueue<>(2);

            q5.put("A");
            q5.put("B");
             q5.put("C"); // would block here if uncommented

            System.out.println(q5.take()); // A
            System.out.println(q5.take()); // B
        } catch (Exception exception) {
            System.out.println("Error : "+ exception.getMessage());
        }





    }
}
