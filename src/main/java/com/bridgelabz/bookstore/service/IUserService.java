package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<ResponseDTO> getAllUserData();
    ResponseEntity<ResponseDTO> getUserByID(int userId);
    ResponseEntity<ResponseDTO> getUserByEmail(String email);
    ResponseEntity<ResponseDTO> getUserByToken(String token);
    ResponseEntity<ResponseDTO> forgetPassword(String email, String psw);
    ResponseEntity<ResponseDTO> changePasswordFromDefault(LoginDTO loginDTO, String psw);
    ResponseEntity<ResponseDTO> registerUser(UserDTO userDTO);
    ResponseEntity<ResponseDTO> updateUserByEmail(String email, UserDTO userDTO);
    ResponseEntity<ResponseDTO> loginUser(LoginDTO loginDTO);
    ResponseEntity<ResponseDTO> deleteByID(int userID);

}
