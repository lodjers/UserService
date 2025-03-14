package com.example.UserService.repositories;

import com.example.UserService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer > {
    public Optional<User> findByName(String name);
}
