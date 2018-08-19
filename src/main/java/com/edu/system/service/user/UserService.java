package com.edu.system.service.user;

import com.edu.system.controller.user.password.ChangePasswordRequest;
import com.edu.system.controller.user.password.CreatePasswordRequest;
import com.edu.system.controller.user.login.LoginRequest;
import com.edu.system.controller.user.login.LoginResult;
import com.edu.system.controller.user.register.RegisterRequest;
import com.edu.system.controller.user.register.RegisterResponse;
import com.edu.system.model.user.User;
import com.edu.system.service.GenericService;

public interface UserService extends GenericService<User> {
    LoginResult login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
    LoginResult createPassword(CreatePasswordRequest request);
    void changePassword(ChangePasswordRequest request);
}
