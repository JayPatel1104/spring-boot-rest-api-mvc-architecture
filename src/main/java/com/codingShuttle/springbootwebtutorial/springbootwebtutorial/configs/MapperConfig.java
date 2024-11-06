package com.codingShuttle.springbootwebtutorial.springbootwebtutorial.configs;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
