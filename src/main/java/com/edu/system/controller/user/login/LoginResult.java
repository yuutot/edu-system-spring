package com.edu.system.controller.user.login;

import com.edu.system.model.user.UserRole;
import lombok.Data;

@Data
public class LoginResult {
    private String login;
    private String csrf;
    private String token;
    private UserRole role;

    public LoginResult(String login, String csrf, String token, UserRole role) {
        this.login = login;
        this.csrf = csrf;
        this.token = token;
        this.role = role;
    }
}
