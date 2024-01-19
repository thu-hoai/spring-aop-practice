package com.example.demo.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
//@DependsOn("circularDependencyA")
public class CircularDependencyA {

    private CircularDependencyB circB;

//    @Autowired(required = false)
//    public void setCircB(CircularDependencyB circB) {
//        this.circB = circB;
//    }
//
//    public CircularDependencyB getCircB() {
//        return circB;
//    }
}