package com.sk.userservice.dtos;

import com.sk.userservice.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private Token token;
}
