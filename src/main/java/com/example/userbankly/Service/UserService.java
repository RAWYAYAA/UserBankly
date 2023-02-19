package com.example.userbankly.Service;

import com.example.userbankly.Config.JwtUtile;
import com.example.userbankly.Entity.Users;
import com.example.userbankly.Repository.UserRepository;
import com.example.userbankly.dto.CredentialsDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtile jwtUtile;

    private final PasswordEncoder passwordEncoder;
    public Users save(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public Users findUserById(Long userId){
        return userRepository.findUserById(userId);
    }
    public Users findByEmail(CredentialsDto credentialsDto) {
        Users user = userRepository.findByEmail(credentialsDto.getEmail()).orElseThrow();
        if (passwordEncoder.matches(credentialsDto.getPassword(),user.getPassword()))
            return user;
        return null;
    }
    public Users signIn(String email,String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }

    public Object validateToken(String token) {
        final String userEmail = jwtUtile.extractUsername(token);
        Users user = userRepository.findByEmail(userEmail).get();
        if (userEmail.equals(user.getEmail()) && !jwtUtile.isTokenExpired(token)) {
            jwtUtile.generateToken(user);
            return user;
        } else {
            return null;
        }
    }
}
