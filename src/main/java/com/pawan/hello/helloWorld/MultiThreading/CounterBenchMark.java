package com.pawan.hello.helloWorld.MultiThreading;

import java.util.concurrent.atomic.AtomicInteger;

class SyncCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet(); // CAS operation
    }

    public int getCount() {
        return count.get();
    }
}

public class CounterBenchMark {
    private static final int THREADS = 10;
    private static final int INCREMENTS = 1_000_000;

    public static void main(String[] args) throws InterruptedException {
        // --- Synchronized Test ---
        SyncCounter syncCounter = new SyncCounter();
        long startSync = System.currentTimeMillis();
        runThreads(() -> {
            for (int i = 0; i < INCREMENTS; i++) {
                syncCounter.increment();
            }
        });
        long endSync = System.currentTimeMillis();
        System.out.println("Synchronized Counter = " + syncCounter.getCount());
        System.out.println("Time taken (synchronized) = " + (endSync - startSync) + " ms");

        // --- Atomic Test ---
        AtomicCounter atomicCounter = new AtomicCounter();
        long startAtomic = System.currentTimeMillis();
        runThreads(() -> {
            for (int i = 0; i < INCREMENTS; i++) {
                atomicCounter.increment();
            }
        });
        long endAtomic = System.currentTimeMillis();
        System.out.println("Atomic Counter = " + atomicCounter.getCount());
        System.out.println("Time taken (atomic) = " + (endAtomic - startAtomic) + " ms");
    }

    private static void runThreads(Runnable task) throws InterruptedException {
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}
