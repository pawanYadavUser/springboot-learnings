//package com.pawan.hello.helloWorld.QueueLL;

import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private Queue<Integer>queue = new LinkedList<>();

    private int bufferSize;

    public SharedBuffer(int size){
        this.bufferSize = size;
    }


    public synchronized void produce(int value) throws InterruptedException {
        while(queue.size() == bufferSize){
            // buffer full → wait for consumer
            wait();
        }
        queue.add(value);
        System.out.println("produced : "+value);

        //notify the consumer
        notify();
    }
    public synchronized int consume() throws InterruptedException {
        while(queue.isEmpty()){
            // buffer full → wait for consumer
            wait();
        }
        int value = queue.poll();
        System.out.println("consumer : "+value);

        //notify the consumer
        notify();
        return value;
    }
}

class Producer implements Runnable {
    private SharedBuffer buffer;
    Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                buffer.produce(i);
                Thread.sleep(500);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}

class Consumer implements Runnable {
    private SharedBuffer buffer;

    Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                buffer.consume();
                Thread.sleep(1000);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}

public class ProducerConsumerWaitNotify {
    public static void main(String[] args) {
        SharedBuffer sharedBuffer = new SharedBuffer(2); //buffer capacity : 2

        Thread produce = new Thread(new Producer(sharedBuffer));
        Thread consume = new Thread(new Consumer(sharedBuffer));

        produce.start();
        consume.start();
    }
}
