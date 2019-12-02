package com.mattindustries.users.domain;

import javax.persistence.*;

import com.mattindustries.data.BaseEntity;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String facebookId;
    @Column(nullable = false, unique = true)
    private String githubId;
}
