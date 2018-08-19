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

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final HttpSession httpSession;

    public UserController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @PostMapping("login")
    public LoginResult login(@RequestBody LoginRequest requestBody) {
        LoginResult result = userService.login(requestBody);
        httpSession.setAttribute("user-login", result.getLogin());
        return result;
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
        LoginResult result = userService.createPassword(requestBody);
        httpSession.setAttribute("user-login", result.getLogin());
        return result;
    }

    @CheckCsrf
    @PutMapping("password")
    @RolesAllowed({UserRole.STUDENT, UserRole.TEACHER, UserRole.ADMIN})
    public void changePassword(@RequestBody ChangePasswordRequest requestBody) {
        userService.changePassword(requestBody);
        httpSession.invalidate();
    }
}
