package com.sk.userservice.dtos;

import com.sk.userservice.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginRequestDto {
    private String password;
    private String email;
}
