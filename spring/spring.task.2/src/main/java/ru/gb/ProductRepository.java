package ru.gb;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("products")
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(new Product(1, "Bread", 100d));
        products.add(new Product(2, "Milk", 200d));
        products.add(new Product(3, "Chicken", 300d));
        products.add(new Product(4, "Meat", 400d));
        products.add(new Product(5, "Beer", 500d));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Optional<Product> getProduct(Integer id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }
}
