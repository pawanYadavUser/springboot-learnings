package com.pawan.hello.helloWorld.MultiThreading;

class DownloadTask extends Thread {
    private String fileName;

    DownloadTask(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        System.out.println("Downloading " + fileName + " in " + Thread.currentThread().getName());
        // simulate I/O work
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
}

public class MultiThreadDemo {
    public static void main(String[] args) {
        new DownloadTask("file1.txt").start();
        new DownloadTask("file2.txt").start();
        new DownloadTask("file3.txt").start();
    }
}

