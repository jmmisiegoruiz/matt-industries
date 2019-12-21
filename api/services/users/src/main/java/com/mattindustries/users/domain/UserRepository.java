package com.mattindustries.users.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByFacebookId(String facebookId);

    User findByGithubId(String facebookId);
}
