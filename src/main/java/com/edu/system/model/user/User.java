package com.edu.system.model.user;

import com.edu.system.model.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    @Column(nullable = false)
    private String login;

    private String password;

    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String accessCode;
}
