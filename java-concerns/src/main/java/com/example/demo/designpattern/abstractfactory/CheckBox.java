package com.example.demo.designpattern.abstractfactory;

public interface CheckBox {
    void render();
    void onChange(Runnable f);
}
