package com.dimandco.proj_studroom.config;

import jakarta.validation.Validator;
import jakarta.validation.Validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Validation Configuration
 */
@Configuration
public class ValidationConfig {

    @SuppressWarnings("resource")
    @Bean
    public Validator validator() {
        return  Validation.buildDefaultValidatorFactory().getValidator();
    }
}
