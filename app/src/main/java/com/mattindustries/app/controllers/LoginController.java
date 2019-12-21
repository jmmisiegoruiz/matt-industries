package com.mattindustries.app.controllers;

import com.mattindustries.app.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/me")
    public ResponseEntity<?> me(Principal principal) {
        return Optional.ofNullable(principal)
                .map(p -> ResponseEntity.ok(this.loginService.getMe(p.getName())))
                .orElse(ResponseEntity.ok().build());
    }
}
