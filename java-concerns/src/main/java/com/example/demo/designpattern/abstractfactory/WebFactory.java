package com.example.demo.designpattern.abstractfactory;

public class WebFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WebButton();
    }

    @Override
    public CheckBox createCheckbox() {
        return new WebCheckbox();
    }
}
