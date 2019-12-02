package com.mattindustries.users.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findUserByFacebookId(String facebookPrincipal);

    User findUserByGithubId(String githubId);
}
