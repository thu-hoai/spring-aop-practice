package com.example.demo.aop.secureaccess;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice {

    private SecurityManager securityManager;

    public SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        UserInfo user = securityManager.getLoggedOnUser();
        if (user == null) {
            System.out.println("No user authenticated");
            throw new SecurityException("Must login before invoking the method " + method.getName());
        }
        if (user.getUserName().equals("chris")) {
            System.out.println("Logged in user is Chris - OK");
        } else {
            throw new SecurityException("User " + user.getUserName() + " is not allowed to access the method " + method.getName());
        }
    }
}
