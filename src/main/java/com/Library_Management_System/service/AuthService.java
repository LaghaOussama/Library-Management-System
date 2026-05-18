package com.Library_Management_System.service;

import com.Library_Management_System.exception.UserException;
import com.Library_Management_System.payload.dto.UserDTO;
import com.Library_Management_System.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(String username, String password);
    AuthResponse signUp(UserDTO req) throws UserException;

    void createPasswordResetToken(String email);
    void resetPassword(String token,String newPassword);

}
