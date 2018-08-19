package com.edu.system.controller.user.login;

import com.edu.system.model.user.UserRole;
import lombok.Data;

@Data
public class LoginResult {
    private String login;
    private String csrf;
    private UserRole role;

    public LoginResult(String login, String csrf, UserRole role) {
        this.login = login;
        this.csrf = csrf;
        this.role = role;
    }
}
