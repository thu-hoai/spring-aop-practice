package com.example.demo.threadsafesingleton;

public enum SingletonEnum {
    INSTANCE;
}

class EnumDemo {

    public static void main(String[] args) {
        SingletonEnum singleton = SingletonEnum.INSTANCE;
    }
}