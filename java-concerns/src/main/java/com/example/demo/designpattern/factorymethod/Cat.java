package com.example.demo.designpattern.factorymethod;

public class Cat implements Pet {

    private String name;
    private boolean isHungry;

    public Cat() {
        super();
        this.isHungry = true;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return "cat";
    }

    @Override
    public boolean isHungry() {
        return this.isHungry;
    }

    @Override
    public void feed() {
        this.isHungry = false;
    }
}
