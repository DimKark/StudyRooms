package com.dimandco.proj_studroom.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestApiClientConfig {

    public static final String BASE_URL = "http://localhost:8081"; // no trailing slash

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
