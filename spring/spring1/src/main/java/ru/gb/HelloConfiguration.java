package ru.gb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.gb")
public class HelloConfiguration {

    @Bean
    public MessageProvider provider() {
        return new WantMessageProvider();
    }

    @Bean
    public MessageRender render() {
        return new ConsoleMessageRender(provider());
    }

}
