package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDTO {
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Please enter a valid email")
    public String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$", message = "Entered Password is invalid")
    public String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
