package com.edu.system.service.token;

import com.edu.system.model.user.User;

public interface TokenService {
    String fromToken(String token);
    String createToken(User user);
}