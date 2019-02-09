package com.edu.system.security;

import com.edu.system.exception.NotFoundException;
import com.edu.system.model.user.User;
import com.edu.system.model.user.UserSessionInfo;
import com.edu.system.service.StoringService;
import com.edu.system.service.token.TokenService;
import com.edu.system.service.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserSessionInterceptor extends HandlerInterceptorAdapter {

    private static final String AUTH_HEADER = "X-AUTH-TOKEN";

    private final UserSessionInfo userSessionInfo;
    private final TokenService tokenService;
    private final UserService userService;
    private final StoringService<String, String> csrfStore;

    public UserSessionInterceptor(UserSessionInfo userSessionInfo, TokenService tokenService, UserService userService, StoringService<String, String> csrfStore) {
        this.userSessionInfo = userSessionInfo;
        this.tokenService = tokenService;
        this.userService = userService;
        this.csrfStore = csrfStore;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String header = request.getHeader(AUTH_HEADER);
        if(header != null) {
            String login = tokenService.fromToken(header);
            User user = userService.findByLogin(login);

            String csrf = csrfStore.get(login);
            if(csrf == null) {
                throw new NotFoundException("CSRF token isn't present");
            }
            fillUserSession(user, csrf);
        }
        return true;
    }

    private void fillUserSession(User user, String csrf) {
        userSessionInfo.setLogin(user.getLogin());
        userSessionInfo.setPassword(user.getPassword());
        userSessionInfo.setRole(user.getUserRole());
        userSessionInfo.setCsrf(csrf);
    }
}