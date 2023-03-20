package com.example.emailemployeeauditing.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull
    @Email
    private String username;//email
    @NotNull
    private String password;
}
