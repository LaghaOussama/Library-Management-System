package com.Library_Management_System.service.impl;

import com.Library_Management_System.Configrations.JwtProvider;
import com.Library_Management_System.domain.UserRole;
import com.Library_Management_System.exception.UserException;
import com.Library_Management_System.modal.User;
import com.Library_Management_System.payload.dto.UserDTO;
import com.Library_Management_System.payload.response.AuthResponse;
import com.Library_Management_System.repository.UserRespository;
import com.Library_Management_System.service.AuthService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {

    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public AuthResponse login(String username, String password) {
        return null;
    }

    @Override
    public AuthResponse signUp(UserDTO req) throws UserException {
        User user=userRespository.findByEmail(req.getEmail());

        if(user!=null){
            throw new UserException("email is already registered");
        }
        User createdUser=new User();
        createdUser.setEmail(req.getEmail());
        createdUser.setPassword(passwordEncoder.encode(req.getPassword()));
        createdUser.setPhone(req.getPhone());
        createdUser.setFullName(req.getFullName());
        createdUser.setLastLogin(LocalDateTime.now());
        createdUser.setRole(UserRole.ROLE_USER);

        User savedUser =  userRespository.save(createdUser);

        Authentication auth =new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = jwtProvider.generateToken(auth);
        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setTitle("Welcome "+savedUser.getFullName());
        response.setMessage("register successfully");
        response.setUser(user);
        return null;
    }

    @Override
    public void createPasswordResetToken(String email) {

    }

    @Override
    public void resetPassword(String token, String newPassword) {

    }
}
