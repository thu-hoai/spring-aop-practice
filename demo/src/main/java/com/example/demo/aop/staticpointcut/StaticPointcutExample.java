package com.example.demo.aop.staticpointcut;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class StaticPointcutExample {
    public static void main(String[] args) {
        BeanOne one = new BeanOne();
        BeanTwo two = new BeanTwo();

        BeanOne proxyOne;
        BeanTwo proxyTwo;

        Pointcut pointcut = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(one);
        proxyOne = (BeanOne) proxyFactory.getProxy();

        proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(two);
        proxyTwo = (BeanTwo) proxyFactory.getProxy();

        proxyOne.foo();
        proxyTwo.foo();

    }
}
