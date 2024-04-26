package com.sk.userservice.dtos;

import com.sk.userservice.models.Role;
import com.sk.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String email;
    private List<Role> roles;
    private Boolean isEmailVerified = false;

    public static UserResponseDto from(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.email = user.getEmail();
        dto.isEmailVerified = user.getIsEmailVerified();
        dto.roles = user.getRoles();
        dto.name = user.getName();
        return dto;
    }
}
