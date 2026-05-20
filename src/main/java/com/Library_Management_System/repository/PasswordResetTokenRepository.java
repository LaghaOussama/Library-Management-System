package com.Library_Management_System.repository;

import com.Library_Management_System.modal.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {

    Optional<PasswordResetToken> findByToken(String token);

}
