package com.pawan.hello.helloWorld.MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {
        // Create a thread pool of 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 5 tasks
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " executing task " + taskId);
            });
        }

        executor.shutdown(); // stop accepting new tasks
    }
}
