package com.edu.system.model.user;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionInfo {

    private String login;
    private String password;
    private UserRole role;
    private String csrf;

    public void invalidate() {
        login = null;
        password = null;
        role = null;
        csrf = null;
    }
}
