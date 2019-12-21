package com.mattindustries.users.controllers;

public class UserNotFoundException extends RuntimeException{

    private Long id;

    private String principal;

    UserNotFoundException(Long id) {
        super("user-not-found-" + id);
        this.id = id;
    }

    UserNotFoundException(String principal) {
        super("user-not-found-" + principal);
        this.principal = principal;
    }

    public Long getId() {
        return id;
    }

    public String getPrincipal() {
        return principal;
    }
}
