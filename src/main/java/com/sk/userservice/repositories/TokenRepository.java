package com.sk.userservice.repositories;

import com.sk.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Override
    Token save(Token token);

    // select * from token where value ='value' and is_deleted = false

    Optional<Token> findByTokenValueAndIsDeleted(String token, boolean isDeleted);

    Optional<Token> findByUserIdAndIsDeleted(Long userId, boolean isDeleted);

}
