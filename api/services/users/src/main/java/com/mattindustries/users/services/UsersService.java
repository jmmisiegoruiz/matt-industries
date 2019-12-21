package com.mattindustries.users.services;

import com.mattindustries.users.domain.User;
import com.mattindustries.users.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class UsersService {

    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

    }

    public Optional<User> getUser(Long id) {
        return this.userRepository.findById(id);
    }

    private Optional<User> getUserByFacebookId(String id) {
        return Optional.ofNullable(this.userRepository.findByFacebookId(id));
    }

    private Optional<User> getUserByGithubId(String id) {
        return Optional.ofNullable(this.userRepository.findByGithubId(id));
    }

    public Optional<User> getUserByPrincipal(String principal) {
        return Stream.of(this.getUserByFacebookId(principal), this.getUserByGithubId(principal))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }
}
