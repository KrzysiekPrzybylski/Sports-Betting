package com.crud.bets.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationDto {

    private String name;
    private String lastName;
    private String email;
    private String password;

}
