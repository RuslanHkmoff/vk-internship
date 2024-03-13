package ru.khakimov.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";


    @Bean
    public WebClient jsonPlaceHolderClient() {
        return WebClient
                .builder()
                .baseUrl(BASE_URI)
                .build();
    }
}
