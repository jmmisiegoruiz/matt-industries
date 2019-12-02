package com.mattindustries.users.controllers;

public class UserNotFoundException extends RuntimeException{

    private final Long id;

    public UserNotFoundException(Long id) {
        super("customer-not-found" + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
