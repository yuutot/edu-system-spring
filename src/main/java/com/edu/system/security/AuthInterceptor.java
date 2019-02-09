package com.edu.system.security;

import com.edu.system.exception.AnautorizedException;
import com.edu.system.exception.ServiceException;
import com.edu.system.model.user.UserRole;
import com.edu.system.model.user.UserSessionInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final UserSessionInfo userSessionInfo;

    public AuthInterceptor(UserSessionInfo userSessionInfo) {
        this.userSessionInfo = userSessionInfo;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            RolesAllowed annotation = handlerMethod.getMethodAnnotation(RolesAllowed.class);
            if (annotation != null) {
                UserRole[] rolesAllowed = annotation.value();
                UserRole userRole = userSessionInfo.getRole();
                if (!ArrayUtils.contains(rolesAllowed, userRole)) {
                    throw new AnautorizedException("You don't have permission to access this url");
                }
            }

        }
        return true;
    }
}