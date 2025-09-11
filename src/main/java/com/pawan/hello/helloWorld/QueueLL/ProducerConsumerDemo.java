package com.pawan.hello.helloWorld.QueueLL;

import java.util.concurrent.*;

class Producer implements Runnable {
    private BlockingQueue<Integer> queue;

    Producer(BlockingQueue<Integer> q) { this.queue = q; }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Produced: " + i);
                queue.put(i); // blocks if full
                Thread.sleep(500);
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    Consumer(BlockingQueue<Integer> q) { this.queue = q; }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                int val = queue.take(); // blocks if empty
                System.out.println("Consumed: " + val);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
    }
}

