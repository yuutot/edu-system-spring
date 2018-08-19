package com.edu.system.controller.user.register;

import com.edu.system.model.user.UserRole;
import lombok.Data;

@Data
public class RegisterRequest {

    private String login;
    private String email;

    private String name;
    private String surname;

    private Integer groupId;

    private UserRole role;
}
