package com.edu.system;

import com.edu.system.security.AuthInterceptor;
import com.edu.system.security.CsrfInterceptor;
import com.edu.system.security.UserSessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final CsrfInterceptor csrfInterceptor;
    private final AuthInterceptor authInterceptor;
    private final UserSessionInterceptor userSessionInterceptor;

    public WebMvcConfig(CsrfInterceptor csrfInterceptor, AuthInterceptor authInterceptor, UserSessionInterceptor userSessionInterceptor) {
        this.csrfInterceptor = csrfInterceptor;
        this.authInterceptor = authInterceptor;
        this.userSessionInterceptor = userSessionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userSessionInterceptor);
        registry.addInterceptor(authInterceptor);
        registry.addInterceptor(csrfInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS");
    }
}