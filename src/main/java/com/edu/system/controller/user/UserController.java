package com.edu.system.controller.user;

import com.edu.system.controller.user.login.LoginRequest;
import com.edu.system.controller.user.login.LoginResult;
import com.edu.system.controller.user.password.ChangePasswordRequest;
import com.edu.system.controller.user.password.CreatePasswordRequest;
import com.edu.system.controller.user.register.RegisterRequest;
import com.edu.system.controller.user.register.RegisterResponse;
import com.edu.system.model.user.UserRole;
import com.edu.system.security.CheckCsrf;
import com.edu.system.security.RolesAllowed;
import com.edu.system.service.user.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public LoginResult login(@RequestBody LoginRequest requestBody) {
        return userService.login(requestBody);
    }

    @CheckCsrf
    @PostMapping("register")
    @RolesAllowed({UserRole.ADMIN})
    public RegisterResponse register(@RequestBody RegisterRequest requestBody) {
        return userService.register(requestBody);
    }

    @CheckCsrf
    @PutMapping("register")
    @RolesAllowed({UserRole.STUDENT, UserRole.TEACHER})
    public LoginResult createPassword(@RequestBody CreatePasswordRequest requestBody) {
        return userService.createPassword(requestBody);
    }

    @CheckCsrf
    @PutMapping("password")
    @RolesAllowed({UserRole.STUDENT, UserRole.TEACHER, UserRole.ADMIN})
    public void changePassword(@RequestBody ChangePasswordRequest requestBody) {
        userService.changePassword(requestBody);
    }
}
