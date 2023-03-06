package com.rubatino.springreact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rubatino.springreact.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
