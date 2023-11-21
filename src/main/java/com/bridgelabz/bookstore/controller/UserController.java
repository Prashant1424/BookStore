package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll(){
        return userService.getAllUserData();
    }

    @GetMapping("/getByID/{userID}")
    public ResponseEntity<ResponseDTO> getByID(@PathVariable int userID){
        return userService.getUserByID(userID);
    }

    @GetMapping("/getByMail")
    public ResponseEntity<ResponseDTO> getByMail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/getByToken/{token}")
    public ResponseEntity<ResponseDTO> getUserByToken(@PathVariable String token){
       return userService.getUserByToken(token);
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO){
        return userService.loginUser(loginDTO);
    }

    @RequestMapping("/passwordReset")
    public ResponseEntity<ResponseDTO> resetPassword(@RequestParam String email, @RequestParam String psw){
        return userService.forgetPassword(email, psw);
    }

    @RequestMapping("/passwordChange")
    public ResponseEntity<ResponseDTO> toChangePassword(@RequestBody LoginDTO loginDTO, @RequestParam String psw){
        return userService.changePasswordFromDefault(loginDTO, psw);
    }

    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> post(@Valid @RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }

    @PutMapping("/put")
    public ResponseEntity<ResponseDTO> update(@RequestParam String email, @RequestBody UserDTO userDTO){
        return userService.updateUserByEmail(email, userDTO);
    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int userID){
        return userService.deleteByID(userID);
    }

}
