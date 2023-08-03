package com.example.demo.designpattern.abstractfactory;

public class WindownCheckbox implements CheckBox {
    @Override
    public void render() {
        System.out.println("Rendering Windown Checkbox");
    }

    @Override
    public void onChange(Runnable f) {
        System.out.println("Windown Checkbox changed");
        f.run();
    }
}
