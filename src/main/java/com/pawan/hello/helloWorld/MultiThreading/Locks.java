package com.pawan.hello.helloWorld.MultiThreading;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Counter {
    private int count = 0;

    public synchronized void increment() { // implicit lock
        count++;
    }

    public int getCount() {
        return count;
    }
}



class Counter1 {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // acquire lock
        try {
            count++;
        } finally {
            lock.unlock(); // always release in finally
        }
    }

    public int getCount() {
        return count;
    }
}


class SharedData {
    private int data = 0;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void writeData(int value) {
        rwLock.writeLock().lock();
        try {
            data = value;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public int readData() {
        rwLock.readLock().lock();
        try {
            return data;
        } finally {
            rwLock.readLock().unlock();
        }
    }
}




public class Locks {
}
