package com.example.demo.threadsafesingleton;

public class OptimizedSynchronizedApproach {

    private static volatile OptimizedSynchronizedApproach instance;

    private OptimizedSynchronizedApproach() {
    }

    public static OptimizedSynchronizedApproach getInstance() {
        if (instance == null) {
            // Block so other threads cannot come into while initialize
            synchronized (OptimizedSynchronizedApproach.class) {
                if (instance == null) {
                    // Re-check again. Maybe another thread has initialized before
                    instance = new OptimizedSynchronizedApproach();
                }
            }
        }
        return instance;
    }
}
