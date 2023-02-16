package com.example.userbankly.Controller;

import com.example.userbankly.Entity.Users;
import com.example.userbankly.Service.UserService;
import com.example.userbankly.dto.UserDto;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@AllArgsConstructor
public class UserController {


    private UserService userService;
    @PostMapping("/user")
    public Users save(@RequestBody Users user){
        return userService.save(user);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<UserDto> signIn(@RequestBody UserDto userDto) {
        ModelMapper modelMapper=new ModelMapper();
        Users user = userService.signIn(userDto.getEmail(), userDto.getPassword());
        userDto = (modelMapper.map(user, UserDto.class));
        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/{id}")
    public Users findUserById(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }
}
