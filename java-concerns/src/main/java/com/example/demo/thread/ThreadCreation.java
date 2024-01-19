package com.example.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCreation {

    public static void main(String[] args) {

        // Pure start
        Thread1 t1 = new Thread1();
//        t1.setName("MyThread-1");

        Thread1 t2 = new Thread1();
//        t2.setName("MyThread-2");

        t1.start();
//        t1.run();
        t2.start();

//        // ExecutorService
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.submit(() -> new Thread1().start());
    }
}

class Thread1 extends Thread {

    @Override
    public void run() {
        System.out.println(this.getName() + "Thread start");
        long startTime = System.currentTimeMillis();
        int i = 0;
        while (true) {
            System.out.println(this.getName() +  ": New Thread is running..." + i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
