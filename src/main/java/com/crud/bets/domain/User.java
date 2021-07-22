package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @NotNull
    private long userId;
    private String name;
    private String lastName;
    private String email;
    private BigDecimal balance = BigDecimal.ZERO;
    private String encryptedPassword;
    private boolean active = true;
    private Set<Role> roles = new HashSet<>();

}
