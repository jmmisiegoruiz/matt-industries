package com.mattindustries.users.configuration;

import com.mattindustries.users.domain.User;
import com.mattindustries.users.domain.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<UserDto, User>() {
            protected void configure() {
                skip().setId((long) 0);
            }
        });
        return modelMapper;
    }

}
