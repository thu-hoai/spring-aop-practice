package com.example.demo.designpattern.factorymethod;

public interface Pet {

    void setName(String name);
    String getName();
    String getType();
    boolean isHungry();
    void feed();
}
