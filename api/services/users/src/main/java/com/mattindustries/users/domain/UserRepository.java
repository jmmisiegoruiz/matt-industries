package com.mattindustries.users.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findFirstByFacebookPrincipalOrGithubPrincipal(String facebookPrincipal, String githubPrincipal);
}
