package com.example.demo.designpattern.abstractfactory;

public class WindowButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Windows button");
    }

    @Override
    public void onClick(Runnable f) {
        System.out.println("Windows Button clicked");
        f.run();
    }
}
