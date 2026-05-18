package com.Library_Management_System.payload.dto;


import com.Library_Management_System.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String phone;
    private String fullName;
    private UserRole role;
    private String userName;

    private LocalDateTime lastLogin;
}
