package com.sk.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel {
    private String tokenValue;
    private Date expireAt;

    @ManyToOne
    private User user;


}
