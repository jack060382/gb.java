package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

/*
+1. Есть класс Product (id, название, цена). Товары хранятся в бине ProductRepository, в виде List<Product>, при старте в него нужно добавить 5 любых товаров.
+2. ProductRepository позволяет получить весь список или один товар по id. Создаем бин Cart, в который можно добавлять и удалять товары по id.
+3. Написать консольное приложение, позволяющее управлять корзиной.
+4. При каждом запросе корзины из контекста, должна создаваться новая корзина.
 */

public class ConsoleApp {
    public static void main(String[] args) {
        System.out.println("Commands (-command):");
        System.out.println("exit (close app)");
        System.out.println("products (show products list)");
        System.out.println("take [id1,id2,...] (Take products and put in a cart)");
        System.out.println("cart (show your cart)");
        //System.out.println("reset (Reset cart and create new)");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ProductRepository repository = context.getBean("repository", ProductRepository.class);
        Cart cart = context.getBean("cart", Cart.class);

        do {
            Scanner in = new Scanner(System.in);
            System.out.print("> ");
            String inputString = in.nextLine();

            if (inputString.equals("cart")) {
                System.out.println("Your cart:");

                for(HashMap.Entry entry : cart.getProducts().entrySet()) {
                    Product product = (Product)entry.getKey();
                    Integer count = (Integer)entry.getValue();
                    System.out.println(product.getTitle()+": "+count);
                }
            }

            if (inputString.startsWith("take")) {
                System.out.println("Takeing products: ");
                String[] comm = inputString.split("\\s");
                for (int i = 1; i < comm.length; i++) {
                    Optional<Product> product = repository.getProduct(Integer.valueOf(comm[i]));
                    if (!product.isEmpty()) {
                        cart.addProduct(product.get());
                        System.out.println(comm[i] + ": " + product.get().getTitle()+" ok.");
                    }
                    else {
                        System.out.println(comm[i]+" not found.");
                    }
                }
            }

            if (inputString.equals("products")) {
                System.out.println("Products list:");
                for (Product product : repository.getProducts()) {
                    System.out.println(product);
                }
            }

            if (inputString.equals("exit")) {
                System.out.println("App close");
                break;
            }

        } while (true);
    }
}
