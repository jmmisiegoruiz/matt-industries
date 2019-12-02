package com.mattindustries.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserDetailsService {

    @RequestMapping("/me")
    public Principal me(Principal principal) {
        return principal;
    }
}
