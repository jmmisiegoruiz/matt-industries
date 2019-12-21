package com.mattindustries.app.services;

import com.mattindustries.app.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service("loginService")
public class LoginService {

    private static final URI GET_USER_BY_PRINCIPAL_URI = URI.create("http://localhost:8080/v1/users/principal/");
    private static final URI POST_USER_URI = URI.create("http://localhost:8080/v1/users");

    private RestTemplate getUserByPrincipalRestTemplate;

    public LoginService(RestTemplate getUserByPrincipalRestTemplate) {
        this.getUserByPrincipalRestTemplate = getUserByPrincipalRestTemplate;
    }

    public UserDto getMe(String principal) {
        return this.getUserByPrincipalRestTemplate.getForObject(GET_USER_BY_PRINCIPAL_URI + principal, UserDto.class);
    }

    private UserDto createUser(String principal) {
        UserDto newUser = new UserDto();
        newUser.setFacebookId(principal);
        newUser.setGithubId(principal);

        return this.getUserByPrincipalRestTemplate.postForObject(POST_USER_URI, newUser, UserDto.class);
    }
}
