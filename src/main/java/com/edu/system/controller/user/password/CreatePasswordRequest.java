package com.edu.system.controller.user.password;

import lombok.Data;

@Data
public class CreatePasswordRequest {
    private String accessCode;
    private String password;
}
