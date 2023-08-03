package com.example.demo.designpattern.abstractfactory;

public class WindowFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowButton();
    }

    @Override
    public CheckBox createCheckbox() {
        return new WindownCheckbox();
    }
}
