package com.example.userbankly.Controller;

import com.example.userbankly.Config.JwtUtile;
import com.example.userbankly.Entity.Users;
import com.example.userbankly.Service.UserService;
import com.example.userbankly.dto.CredentialsDto;
import com.example.userbankly.dto.ResponseDto;
import com.example.userbankly.dto.UserDto;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@AllArgsConstructor
public class UserController {


    private UserService userService;
    private final JwtUtile jwtUtils;

    @PostMapping(path = "/user")
    public ResponseEntity<ResponseDto> register(@RequestBody  UserDto userDTO){
        ModelMapper modelMapper=new ModelMapper();
        Users user = modelMapper.map(userDTO, Users.class);
        user = userService.save(user);
        ResponseDto response = new ResponseDto() ;
        response.setData(modelMapper.map(user, UserDto.class));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> login(@RequestBody  CredentialsDto credentialsDto){
        try {
            Users user = userService.findByEmail(credentialsDto);
            ResponseDto response = new ResponseDto() ;
            if (user != null){
                response.setMessage(jwtUtils.generateToken(user));
                response.setStatus("YES");
                response.setData(user);
            }else {
                response.setMessage("user not found");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ResponseDto response = new ResponseDto() ;
            response.setStatus("ERROR");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @GetMapping("/validate-token/{token}")
    public UserDto validateToken(@PathVariable("token") String token){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(userService.validateToken(token),UserDto.class);
    }

    @GetMapping("/{id}")
    public Users findUserById(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }
}
