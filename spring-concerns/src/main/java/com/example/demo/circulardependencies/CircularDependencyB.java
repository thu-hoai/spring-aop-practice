package com.example.demo.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularDependencyB {

    private CircularDependencyA circA;

//    @Autowired(required = false)
//    public void setCircA(CircularDependencyA circA) {
//        this.circA = circA;
//    }
}