package com.example.demo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ExceptionTest {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(3, 9, 7, 6, 10, 0, 20);
        // without wrapper
        integers.forEach(i -> {
            try {
                System.out.println(50 / i);
            } catch (ArithmeticException e) {
                System.err.println(e.getMessage());
            }
            System.out.println(50 / i);
        });

        // with wrapper
        integers.forEach(lambdaWrapper(i -> System.out.println(50 / i)));

        // check exp
    }

    static Consumer<Integer> lambdaWrapper(Consumer<Integer> consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (ArithmeticException e) {
                System.err.println(e.getMessage());
            }
        };
    }

    static <T, E extends Exception> Consumer<T> lambdaWrapperMoreConsice(Consumer<T> consumer, Class<E> clazz) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception e) {
                E exp = clazz.cast(e);
                System.err.println(exp.getMessage());
            }
        };
    }
}
