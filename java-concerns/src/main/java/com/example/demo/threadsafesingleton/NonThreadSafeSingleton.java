package com.example.demo.threadsafesingleton;

public class NonThreadSafeSingleton {

    private static NonThreadSafeSingleton instance;

    private NonThreadSafeSingleton() {}

    public static NonThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new NonThreadSafeSingleton();
        }
        return instance;
    }
}
