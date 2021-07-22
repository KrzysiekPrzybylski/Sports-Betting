package com.crud.bets.domain.dto;

import com.crud.bets.domain.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
public class UserDto {
    private long userId;
    private String name;
    private String lastName;
    private String email;
    private BigDecimal balance;
    private String encryptedPassword;
    private boolean active;
    @Getter(AccessLevel.NONE)
    private final Set<String> roles;

    public Set<String> getRoles() {
        return new HashSet<>(roles);
    }
}
