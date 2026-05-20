package com.Library_Management_System.controller;

import com.Library_Management_System.exception.UserException;
import com.Library_Management_System.payload.dto.UserDTO;
import com.Library_Management_System.payload.request.ForgotPasswordRequest;
import com.Library_Management_System.payload.request.LoginRequest;
import com.Library_Management_System.payload.request.ResetPasswordRequest;
import com.Library_Management_System.payload.response.ApiResponse;
import com.Library_Management_System.payload.response.AuthResponse;
import com.Library_Management_System.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
@PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
        @Valid @RequestBody  UserDTO req
            ) throws UserException {
        AuthResponse res =authService.signUp(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @Valid @RequestBody  LoginRequest req
    ) throws UserException {
        AuthResponse res =authService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @RequestBody ForgotPasswordRequest req
    ) throws UserException {
        authService.createPasswordResetToken(req.getEmail());
        ApiResponse res =new ApiResponse("A reset link was sent to your email.",true);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @RequestBody ResetPasswordRequest req
    ) throws  Exception {
        authService.resetPassword(req.getToken(),req.getPassword());
        ApiResponse res =new ApiResponse("Password reset success.",true);
        return ResponseEntity.ok(res);
    }

}
