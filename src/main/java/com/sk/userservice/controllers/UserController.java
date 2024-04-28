package com.sk.userservice.controllers;

import com.sk.userservice.dtos.*;
import com.sk.userservice.exceptions.InvalidTokenException;
import com.sk.userservice.models.Token;
import com.sk.userservice.models.User;
import com.sk.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignUpRequestDto userDto) {

        User user = userService.signUp(userDto.getEmail(),
                userDto.getPassword(),
                userDto.getName());


        return new ResponseEntity<>(UserResponseDto.from(user), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return null;
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, SignUpRequestDto userDto) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        Token token = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LogoutRequestDto logoutRequestDto) {
        try {
            userService.logout(logoutRequestDto.getToken());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidTokenException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
