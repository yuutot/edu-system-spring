package com.edu.system.security;

import com.edu.system.model.user.UserRole;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RolesAllowed {
    UserRole[] value() default {UserRole.ADMIN};
}
