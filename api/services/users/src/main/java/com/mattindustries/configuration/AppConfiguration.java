package com.mattindustries.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(mapper -> {
            mapper.map(src -> src.get().getStreet(),
                    Destination::setBillingStreet);
            mapper.map(src -> src.getBillingAddress().getCity(),
                    Destination::setBillingCity);
        });
        return modelMapper;
    }

}
