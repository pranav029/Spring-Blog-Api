package com.spring.blog.BlogApp.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
