package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate DOB;
    private String password;

    public UserData(UserDTO userDTO) {
        this.firstName = userDTO.firstName;
        this.lastName = userDTO.lastName;
        this.email = userDTO.email;
        this.address = userDTO.address;
        this.DOB = userDTO.DOB;
        this.password = userDTO.password;
    }
}
