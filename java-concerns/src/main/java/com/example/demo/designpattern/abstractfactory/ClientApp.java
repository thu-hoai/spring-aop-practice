package com.example.demo.designpattern.abstractfactory;

public class ClientApp {

    private final Button button;
    private final CheckBox checkBox;

    public ClientApp(GUIFactory factory) {
        button = factory.createButton();
        checkBox = factory.createCheckbox();
    }

    public void render() {
        button.render();
        checkBox.render();
    }

    public static void main(String[] args) {
        String env = "Windows";
        GUIFactory factory;
        if (env.equalsIgnoreCase("windows")) {
            factory = new WindowFactory();
        } else {
            factory = new WebFactory();
        }

        ClientApp app = new ClientApp(factory);
        app.render();
    }
}
