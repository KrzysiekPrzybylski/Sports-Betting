package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "ROLES")
@Data
public class Role {
    @Id
    @GeneratedValue
    @NotNull
    private long roleId;
    @NotNull
    private String role = "USER";

}
