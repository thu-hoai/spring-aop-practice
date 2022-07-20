package com.example.demo.aop.beforeadvice;

import com.example.demo.aop.MessageWriter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before method " + method.getName());
    }

    public static void main(String[] args) {
        MessageWriter target = new MessageWriter();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleBeforeAdvice());
        proxyFactory.setTarget(target);

        MessageWriter proxy = (MessageWriter) proxyFactory.getProxy();
        proxy.writeMessage();
    }
}
