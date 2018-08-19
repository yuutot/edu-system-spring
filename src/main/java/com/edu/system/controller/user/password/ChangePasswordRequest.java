package com.edu.system.controller.user.password;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String old;
    private String password;
}
