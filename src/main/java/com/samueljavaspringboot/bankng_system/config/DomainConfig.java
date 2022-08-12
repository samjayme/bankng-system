package com.samueljavaspringboot.bankng_system.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DomainConfig {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
