package com.example.userbankly.Repository;


import com.example.userbankly.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users,Long> {
    Users findUserById(Long userId);
    Users findByEmailAndPassword(String email,String password);
    Optional<Users> findByEmail(String email);
  }
