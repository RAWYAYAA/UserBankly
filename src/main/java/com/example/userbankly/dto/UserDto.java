package com.example.userbankly.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    private String password;
    private String email;
    private String FirstNAme;
    private String LastName;
}
