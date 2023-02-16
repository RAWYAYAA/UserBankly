package com.example.userbankly.Service;

import com.example.userbankly.Entity.Users;
import com.example.userbankly.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;
    public Users save(Users user){
        return userRepository.save(user);
    }
    public Users findUserById(Long userId){
        return userRepository.findUserById(userId);
    }
    public Users signIn(String email,String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }
}
