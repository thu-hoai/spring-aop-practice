package com.example.demo.designpattern.abstractfactory;

public class WebButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Web button");
    }

    @Override
    public void onClick(Runnable f) {
        System.out.println("Web Button clicked");
        f.run();
    }
}
