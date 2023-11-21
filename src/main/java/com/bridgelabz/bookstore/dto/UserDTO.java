package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Entered First name is invalid")
    public String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Entered Last name is invalid")
    public String lastName;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Please enter a valid email")
    public String email;
    @NotEmpty(message = "Address field can't be null")
    public String address;
    public LocalDate DOB;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$", message = "Entered Password is invalid")
    public String password;

    public UserDTO(String firstName, String lastName, String email, String address, LocalDate DOB, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.DOB = DOB;
        this.password = password;
    }
}
