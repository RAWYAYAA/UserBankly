package com.example.userbankly.Repository;


import com.example.userbankly.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users,Long> {
    Users findUserById(Long userId);
    Users findByEmailAndPassword(String email,String password);
    //UserDetails findUserByEmail(String email);
  }
