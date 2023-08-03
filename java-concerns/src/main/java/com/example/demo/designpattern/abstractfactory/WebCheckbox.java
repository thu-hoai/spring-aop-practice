package com.example.demo.designpattern.abstractfactory;

public class WebCheckbox implements CheckBox {
    @Override
    public void render() {
        System.out.println("Rendering Web Checkbox");
    }

    @Override
    public void onChange(Runnable f) {
        System.out.println("Web Checkbox changed");
        f.run();
    }
}
