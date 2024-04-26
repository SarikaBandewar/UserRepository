package com.sk.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String hashedPassword;
    private String email;
    @ManyToMany
    private List<Role> roles;
    private Boolean isEmailVerified = false;
    private Boolean isDeleted = false;
}
