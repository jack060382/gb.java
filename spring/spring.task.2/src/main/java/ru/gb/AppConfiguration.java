package ru.gb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.gb")
public class AppConfiguration {

    @Bean
    public ProductRepository repository() {
        return new ProductRepository();
    }

    @Bean
    public Cart cart() {
        return new Cart();
    }

}
