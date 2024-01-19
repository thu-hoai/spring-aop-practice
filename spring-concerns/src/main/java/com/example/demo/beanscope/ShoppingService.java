package com.example.demo.beanscope;
// auto import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingService {
    private final ShoppingCart shoppingCart;
    private final Calculator calculator;

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    public ShoppingService(ShoppingCart shoppingCart, Calculator calculator) {
        this.shoppingCart = shoppingCart;
        this.calculator = calculator;
    }

    public void addItemToCart(String item) {
        shoppingCart.addItem(item);
    }

    public List<String> getCartItems() {
        return shoppingCart.getItems();
    }

    public int calculateTotal(int price1, int price2) {
        return calculator.add(price1, price2);
    }
}