package com.example.demo.beanscope;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.util.Random;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
public class MySingletonBean {
    @Autowired
    private ObjectProvider<MyPrototypeBean> myPrototypeBeanProvider;

//    @Autowired
//    private MyPrototypeBean myPrototypeBeanProvider;

    public void doSomething() {
        MyPrototypeBean myPrototypeBean = myPrototypeBeanProvider.getIfAvailable();
        System.out.println("myPrototypeBean = " + myPrototypeBean);

//        System.out.println("myPrototypeBean = " + myPrototypeBeanProvider);
    }
}

@Component
@Scope(SCOPE_PROTOTYPE)
class MyPrototypeBean {
    public void performAction() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        // Generates a random integer between 0 (inclusive) and 100 (exclusive)
        System.out.println("Random number: " + randomNumber);
    }
}