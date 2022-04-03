package ru.gb;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("cart")
public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        Integer count = products.getOrDefault(product, 0);
        products.put(product, count + 1);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}
