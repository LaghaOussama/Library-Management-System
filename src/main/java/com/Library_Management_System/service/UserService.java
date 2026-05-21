package com.Library_Management_System.service;

import com.Library_Management_System.modal.User;
import com.Library_Management_System.payload.dto.UserDTO;

import java.util.List;

public interface UserService {
    public User getCurrentUser() throws Exception;
    public List<UserDTO> getAllUsers();
}
