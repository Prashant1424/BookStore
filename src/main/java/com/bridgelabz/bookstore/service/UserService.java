package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.email.EmailSenderService;
import com.bridgelabz.bookstore.exception.PasswordNotFoundException;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.UserData;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService {

//    (Register,getAll,getbyID,getbyEmailID,forgotpassword/resetPassword,changePassword,updateuserbyEmail,
//    login,deleteById)

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public ResponseEntity<ResponseDTO> getAllUserData() {

            ResponseDTO responseDTO = new ResponseDTO("Get Call Successful", userRepository.findAll());

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO> getUserByID(int userId) {

        UserData userData = userRepository.findById(userId).orElseThrow(()-> new UserException("Please enter a valid ID."));

        ResponseDTO responseDTO = new ResponseDTO("Get Call by ID is Successful", userData);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO> getUserByEmail(String email) {

        UserData userData = userRepository.getUserByEmail(email);

        if (userData != null){

            ResponseDTO responseDTO = new ResponseDTO("Get Call by mail is Successful", userData);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } else {

            throw new UserException("User with email id : " +email + " not found.");

        }

    }

    @Override
    public ResponseEntity<ResponseDTO> getUserByToken(String token){

        int userId = tokenUtil.decodeToken(token);

        ResponseDTO responseDTO = new ResponseDTO("Get call by token is successful", userRepository.findById(userId).get());

        return  new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO> forgetPassword(String email, String psw) {

        UserData userData = userRepository.getUserByEmail(email);

        if (userData != null) {

            userData.setPassword(psw);

            userRepository.save(userData);

            emailSenderService.sendEmail(userData.getEmail(), "Password is successfully reset.", "The password has been reset.");

            ResponseDTO responseDTO = new ResponseDTO("Password has been reset", userData);

            return new ResponseEntity<>(responseDTO,HttpStatus.OK);

        } else {

            throw new UserException("EmailId Incorrect...!");

        }

    }

    @Override
    public ResponseEntity<ResponseDTO> changePasswordFromDefault(LoginDTO loginDTO, String psw) {

        UserData userData = userRepository.getUserByEmail(loginDTO.getEmail());

        if (userData != null){

            if (userData.getPassword().equals(loginDTO.getPassword())) {

                userData.setPassword(psw);

                userRepository.save(userData);

                emailSenderService.sendEmail(userData.getEmail(), "Password changed Successfully.", "The password has been changed successfully.");

                ResponseDTO responseDTO = new ResponseDTO("Password has been changed successfully for the first time", userData);

                return new ResponseEntity<>(responseDTO,HttpStatus.OK);

            } else {

                throw new PasswordNotFoundException("Old Password is Incorrect...!");

            }

        }else {

            throw new UserException("User not found. Check emailId...!");

        }

    }

    @Override
    public ResponseEntity<ResponseDTO> registerUser(UserDTO userDTO) {

        UserData userData = new UserData(userDTO);

        userRepository.save(userData);

        String token = tokenUtil.createToken(userData.getUserId());

        System.out.println(token);

        emailSenderService.sendEmail(userData.getEmail(), "Registration Done Successfully.", "New user has been added. The name of the person is: " + userData.getFirstName() +
                '\n' + "To get the details click the link: http://localhost:9098/user/getByToken/" + token);

        ResponseDTO responseDTO = new ResponseDTO("Created Address Book Data", userData);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @Override
    public ResponseEntity<ResponseDTO> updateUserByEmail(String email, UserDTO userDTO) {

        UserData userData = userRepository.getUserByEmail(email);

        if (userData != null) {

            userData.setPassword(userDTO.password);

            userData.setDOB(userDTO.DOB);

            userData.setAddress(userDTO.address);

            userData.setFirstName(userDTO.firstName);

            userData.setLastName(userDTO.lastName);

            userRepository.save(userData);

            emailSenderService.sendEmail(userData.getEmail(), "User details update", "The password has been updated successfully.");

            ResponseDTO responseDto = new ResponseDTO("Put call successful", userData);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);

        } else {

            throw new UserException("Incorrect EmailId.");

        }

    }

    @Override
    public ResponseEntity<ResponseDTO> loginUser(LoginDTO loginDTO) {

        UserData userData = userRepository.getUserByEmail(loginDTO.getEmail());

        if (userData != null && userData.getPassword().equals(loginDTO.getPassword())) {

            ResponseDTO responseDTO = new ResponseDTO("Login Successful", userData);

            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);

        } else {

            throw new UserException("Either Username or Password is Invalid");

        }

    }

    @Override
    public ResponseEntity<ResponseDTO> deleteByID(int userID) {

        if (userRepository.existsById(userID)) {

            String toEmail= userRepository.findById(userID).get().getEmail();

            userRepository.deleteById(userID);

            emailSenderService.sendEmail(toEmail, "User deleted Successfully.", "The user has been deleted.");

            ResponseDTO responseDTO = new ResponseDTO("User Deleted Successfully", false);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } else {

            throw new UserException("UserId Entered is Incorrect.");

        }

    }

}
