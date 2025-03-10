package com.marco.Esportify.repository;

import com.marco.Esportify.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> getUserByEmail(String email);
}
