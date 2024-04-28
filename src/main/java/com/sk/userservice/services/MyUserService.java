package com.sk.userservice.services;

import com.sk.userservice.exceptions.InvalidTokenException;
import com.sk.userservice.models.Token;
import com.sk.userservice.models.User;
import com.sk.userservice.repositories.TokenRepository;
import com.sk.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class MyUserService implements UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MyUserService(UserRepository userRepository,
                         TokenRepository tokenRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User signUp(String email, String password, String name) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        // login successful, generate new token
        Token token = generateToken(user);
        return tokenRepository.save(token);
    }

    private Token generateToken(User user) {
        LocalDate localDate = LocalDate.now();
        LocalDate thirtyDaysFromCurrentDate = localDate.plusDays(30);
        Date expDate = Date.from(thirtyDaysFromCurrentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setUser(user);
        token.setExpireAt(expDate);
        token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));

        return token;
    }

    @Override
    public void logout(String token) throws InvalidTokenException {
        Optional<Token> optionalToken = tokenRepository.findByTokenValueAndIsDeleted(
                token, false
        );

        if (optionalToken.isEmpty()) {
            throw new InvalidTokenException();
        }
        Token toDelete = optionalToken.get();
        toDelete.setIsDeleted(true);
        tokenRepository.save(toDelete);
    }
}
