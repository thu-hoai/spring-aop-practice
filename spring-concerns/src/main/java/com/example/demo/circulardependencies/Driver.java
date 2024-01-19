package com.example.demo.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Driver {
    private Car car;

//    @Autowired(required = false)
//    public void setCar(Car car) {
//        this.car = car;
//    }
//
//    public void showCar() {
//        System.out.println("my car: " + car);
//    }
}