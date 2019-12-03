package com.mattindustries.users.domain;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String facebookId;
    private String githubId;
}
