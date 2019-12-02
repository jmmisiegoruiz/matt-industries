package com.mattindustries.users.controllers;

import com.mattindustries.users.domain.User;
import com.mattindustries.users.domain.UserRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/users")
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        return ResponseEntity
                .ok()
                .allow(
                        HttpMethod.OPTIONS,
                        HttpMethod.GET,
                        HttpMethod.POST,
                        HttpMethod.PUT,
                        HttpMethod.DELETE,
                        HttpMethod.HEAD
                ).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity
                .ok(this.userRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return this.userRepository.findById(id).map(
                ResponseEntity::ok
        ).orElseThrow(() -> new UserNotFoundException(id));
    }
    
    @PostMapping
    ResponseEntity<User> post(@RequestBody User user) {

        User userToSave = new User();
        userToSave.setFirstName(user.getFirstName());
        userToSave.setLastName(user.getLastName());
        userToSave.setFacebookId(user.getFacebookId());
        user.setGithubId(user.getGithubId());

        User savedUser = this.userRepository.save(userToSave);

        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }
    
}
