package com.example.demo.java8;

public class DefaultMethodTest {

    public static void main(String[] args) {

    }
}

interface InterfaceA {
    default void foo() {
        System.out.println("InterfaceA foo");
    }
}

interface InterfaceB {
    default void foo() {
        System.out.println("InterfaceB foo");
    }
}

class ClassA implements InterfaceA, InterfaceB {
    @Override
    public void foo() {
        InterfaceA.super.foo();
    }
}

abstract class AbstractClassA {
    public abstract void foo();
}

class AbstractClassB extends AbstractClassA {
    @Override
    public void foo() {
    }
}

class AbstractClassC extends AbstractClassA {
    @Override
    public void foo() {
    }
}