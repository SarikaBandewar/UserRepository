package com.sk.userservice.controllers;

import com.sk.userservice.dtos.LoginRequestDto;
import com.sk.userservice.dtos.SignUpRequestDto;
import com.sk.userservice.dtos.UserResponseDto;
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
    public Token login(@RequestBody LoginRequestDto loginRequestDto) {
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LoginRequestDto loginRequestDto) {
        return null;
    }

}
