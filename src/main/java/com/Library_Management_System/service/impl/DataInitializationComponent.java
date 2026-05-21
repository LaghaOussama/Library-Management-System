package com.Library_Management_System.service.impl;


import com.Library_Management_System.domain.UserRole;
import com.Library_Management_System.modal.User;
import com.Library_Management_System.repository.UserRespository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRespository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run (String... args){
        initializeAdminUser();
    }
    private void initializeAdminUser(){
        String adminEmail="lagha.1993@gmail.com";
        String adminPassword="123456";

        if(userRepository.findByEmail(adminEmail)==null){
            User user = User.builder()
                    .password(passwordEncoder.encode(adminPassword))
                    .email(adminEmail)
                    .fullName(adminEmail)
                    .role(UserRole.ROLE_ADMIN)
                    .build();

            User savedUser = userRepository.save(user);
        }
    }
}
