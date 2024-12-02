package com.springsecurity.springsecurity.entities;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email;
    private String currentPassword;
    private String newPassword;
}
