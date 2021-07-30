package com.crud.bets.domain.detail;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
public class LoginTryDateTime {

    @NotNull
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private LocalDateTime dateTime;

    public LoginTryDateTime() {
        dateTime = LocalDateTime.now();
    }
}
