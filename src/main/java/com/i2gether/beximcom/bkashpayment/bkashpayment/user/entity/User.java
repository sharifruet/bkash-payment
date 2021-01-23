package com.i2gether.beximcom.bkashpayment.bkashpayment.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    //@NotNull(message = "Name is mandatory")
    private String name;
    
    //@NotBlan (message = "Email is mandatory")
    private String username;

    private String password;
    
    private String role;
    
}