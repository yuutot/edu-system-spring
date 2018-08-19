package com.edu.system.controller.user.register;

import com.edu.system.model.StudentGroup;
import com.edu.system.model.user.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {

    private String login;
    private String email;

    private String name;
    private String surname;

    private StudentGroup group;

    private UserRole role;
    private String accessCode;

}
