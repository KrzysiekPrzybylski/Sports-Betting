package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
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
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private BigDecimal balance = BigDecimal.ZERO;
    @NotNull
    private String encryptedPassword;
    @NotNull
    private boolean active = true;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name ="USER_ROLES",
            joinColumns = @JoinColumn(name="USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles = new HashSet<>();
    @OneToMany(cascade =  CascadeType.All, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Set<Slip> slips = new HashSet<>();

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CARTSLIP_ID")
    private Slip cartSlip = new Slip();

    public void addToBalance(BigDecimal value) {
        balance=balance.add(value);
    }
}
