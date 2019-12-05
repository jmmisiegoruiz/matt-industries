package com.mattindustries.users.controllers;

import com.mattindustries.users.domain.User;
import com.mattindustries.users.domain.UserDto;
import com.mattindustries.users.services.UsersService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/users")
public class UsersController {

    private final UsersService usersService;

    private final ModelMapper modelMapper;

    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
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
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity
                .ok(this.usersService.getUsers().stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList())
                );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return this.usersService.getUser(id).map(user -> ResponseEntity.ok(convertToDto(user))
        ).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {

        User user = this.usersService.saveUser(convertToEntity(userDto));

        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(convertToDto(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return this.usersService.getUser(id).map(user -> {
                    this.usersService.deleteUser(user);
                    return ResponseEntity.noContent().build();
                }
        ).orElseThrow(() -> new UserNotFoundException(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<?> checkUser(@PathVariable Long id) {
        return this.usersService.getUser(id).map(user -> ResponseEntity.noContent().build()
        ).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<UserDto> putUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return this.usersService.getUser(id)
                .map(existingUser -> new User(id,
                        userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getFacebookId(),
                        userDto.getGithubId()))
                .map(newUser -> {
                            this.usersService.saveUser(newUser);
                            URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
                            return ResponseEntity.created(selfLink).body(convertToDto(newUser));
                        }
                ).orElseThrow(() -> new UserNotFoundException(id));
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
