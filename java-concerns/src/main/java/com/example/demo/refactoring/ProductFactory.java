package com.example.demo.refactoring;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {
  static final Map<String, Supplier<Product>> map = new HashMap<>();

  static {
    map.put("loan", Loan::new);
    map.put("stock", Stock::new);
    map.put("bond", Bond::new);
  }

  public static void main(String[] args) {

    // normal
    Product loan = ProductFactory.createProduct("loan");
    Product stock = ProductFactory.createProduct("stock");
    Product bond = ProductFactory.createProduct("bond");
  }

  public static Product createProduct(String name) {
    switch (name) {
      case "loan":
        return new Loan();
      case "stock":
        return new Stock();
      case "bond":
        return new Bond();
      default:
        throw new RuntimeException("No such product " + name);
    }

//    Supplier<Product> p = map.get(name);
//    if (p != null) return p.get();
//    throw new IllegalArgumentException("No such product " + name);
  }
}

abstract class Product {
}

class Loan extends Product {
}

class Stock extends Product {
}

class Bond extends Product {
}