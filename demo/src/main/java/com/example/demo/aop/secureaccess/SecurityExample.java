package com.example.demo.aop.secureaccess;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityExample {
    public static void main(String[] args) {
        SecurityManager securityManager = new SecurityManager();
        SecureBean bean = getSecureBean();

        // valid user
        securityManager.login("chris", "any");
        bean.writeSecureMessage();
        securityManager.logout();

        // unauthen user
        try {
            securityManager.login("any", "any");
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        } finally {
            securityManager.logout();
        }

        // unathen user
        try {
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }
    }

    private static SecureBean getSecureBean() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new SecureBean());
        proxyFactory.addAdvice(new SecurityAdvice());

        return  (SecureBean) proxyFactory.getProxy();
    }
}
