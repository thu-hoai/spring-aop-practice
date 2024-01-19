package com.example.demo.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    private Driver driver;

    @Autowired(required = false)
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void drive() {
        System.out.println("driven by: " + driver);
    }
}