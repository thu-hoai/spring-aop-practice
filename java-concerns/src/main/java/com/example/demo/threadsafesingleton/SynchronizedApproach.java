package com.example.demo.threadsafesingleton;

public class SynchronizedApproach {

    private static SynchronizedApproach instance;

    private SynchronizedApproach() {}

    /* synchronized a whole method */
    public synchronized SynchronizedApproach getInstance() {
        if (instance == null) {
            instance = new SynchronizedApproach();
        }
        return instance;
    }
}
