package com.edu.system.security;

import com.edu.system.model.user.UserSessionInfo;
import com.edu.system.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CsrfInterceptor extends HandlerInterceptorAdapter {

    private static final String CSRF_TOKEN = "X-CSRF-TOKEN";

    @Autowired
    private UserSessionInfo userSessionInfo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            CheckCsrf annotation = handlerMethod.getMethodAnnotation(CheckCsrf.class);
            if (annotation != null) {
                String csrfToken = request.getHeader(CSRF_TOKEN);
                if (StringUtils.isEmpty(userSessionInfo.getCsrf()) || !StringUtils.equals(userSessionInfo.getCsrf(), csrfToken)) {
                    throw new SecurityException("Invalid csrf token");
                }
            }
        }
        return true;
    }
}