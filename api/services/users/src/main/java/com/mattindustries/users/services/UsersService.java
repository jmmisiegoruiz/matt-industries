package com.mattindustries.users.services;

import com.mattindustries.users.domain.User;
import com.mattindustries.users.domain.UserRepository;
import com.mattindustries.utils.OauthProvider;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UsersService {

    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByPrincipal(Principal principal, OauthProvider provider) {
        return Optional.ofNullable(principal)
                .map(p ->
                        userRepository.findFirstByFacebookPrincipalOrGithubPrincipal(p.getName())
                ).orElse(null);
    }
}
