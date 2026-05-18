package com.Library_Management_System.repository;

import com.Library_Management_System.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
