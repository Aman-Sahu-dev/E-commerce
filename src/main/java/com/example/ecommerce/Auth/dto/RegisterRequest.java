package com.example.ecommerce.Auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @Pattern(regexp = "[0-9]{10}$")
    private String phone;
    @NotBlank
    @Size(min = 8 , max = 100000)
    private String password;
}
