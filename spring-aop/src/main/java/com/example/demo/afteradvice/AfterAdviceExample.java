package com.example.demo.afteradvice;

import org.springframework.aop.framework.ProxyFactory;

import java.security.Key;

public class AfterAdviceExample {
    public static void main(String[] args) {
        KeyGenerator keyGenerator = getKeyGenerator();

        for (int i = 0; i < 10; i++ ) {
            try {
                long key = keyGenerator.getKey();
                System.out.println("Key" + key);
            } catch (SecurityException e) {
                System.out.println("Weak Key Generated!");
            }
        }
    }

    private static KeyGenerator getKeyGenerator() {
        KeyGenerator target = new KeyGenerator();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new WeakKeyCheckAdvice());
        proxyFactory.setTarget(target);
        proxyFactory.getProxy();

        return (KeyGenerator) proxyFactory.getProxy();
    }
}
