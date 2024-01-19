package com.example.demo.builder;

import static com.example.demo.builder.NyPizza.Size.SMALL;

public class PizzaBuilderTest {

    public static void main(String[] strings) {
        NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(Pizza.Topping.SAUSAGE).build();
    }
}
