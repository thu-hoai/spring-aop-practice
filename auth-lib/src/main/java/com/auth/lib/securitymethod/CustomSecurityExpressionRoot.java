package com.auth.lib.securitymethod;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class CustomSecurityExpressionRoot  extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    protected final Authentication authenticationObj;
    private Object filterObj;
    private Object returnObj;
    private Method method;
    private Object target;
    public CustomSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
        this.authenticationObj = authentication;
    }

    public boolean hasAuthorityToSpecificAPI() {
        Set<GrantedAuthority> userAuthority = new HashSet<>(authenticationObj.getAuthorities());

        return userAuthority.stream()
            .anyMatch(authority ->
                authority.equals(method.getName()));
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObj = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObj;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObj = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObj;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    public void setMethod(Method m) {
        this.method = m;
    }
}
