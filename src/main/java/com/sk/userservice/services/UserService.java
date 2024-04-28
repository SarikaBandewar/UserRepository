package com.sk.userservice.services;

import com.sk.userservice.exceptions.InvalidTokenException;
import com.sk.userservice.models.Token;
import com.sk.userservice.models.User;

public interface UserService {
    User signUp(String email, String password, String name);
    Token login(String email, String password);
    void logout(String token) throws InvalidTokenException;
}
