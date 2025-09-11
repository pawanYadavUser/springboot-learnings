package com.pawan.hello.helloWorld.MultiThreading;

class SharedBuffer {
    private int data;
    private boolean hasData = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (hasData) {
            wait(); // wait if data not consumed
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); // wake up consumer
    }

    public synchronized void consume() throws InterruptedException {
        while (!hasData) {
            wait(); // wait if no data available
        }
        System.out.println("Consumed: " + data);
        hasData = false;
        notify(); // wake up producer
    }
}

public class NotifyDemo {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try { buffer.produce(i); Thread.sleep(500); } catch (Exception ignored) {}
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try { buffer.consume(); Thread.sleep(1000); } catch (Exception ignored) {}
            }
        });

        producer.start();
        consumer.start();
    }
}
