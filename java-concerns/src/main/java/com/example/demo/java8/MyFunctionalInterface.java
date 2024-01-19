package com.example.demo.java8;

// Functional Interface with a static method
public interface MyFunctionalInterface {
    // Abstract method (functional method)
    void myAbstractMethod();

    // Static method in interface (introduced in Java 9)
    static void myStaticMethod() {
        System.out.println("This is a static method in the interface.");
    }
    private static void test(){}
}

// A class implementing the functional interface
class MyClass implements MyFunctionalInterface {
    @Override
    public void myAbstractMethod() {
        System.out.println("Implementation of the abstract method.");
    }


    public static void main(String[] args) {
        // Calling the static method of the interface
        MyFunctionalInterface.myStaticMethod();

        // Creating an instance of the implementing class
        MyClass myClass = new MyClass();

        // Calling the abstract method through the instance
        myClass.myAbstractMethod();

    }
}