package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
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
    @Size(min = 2, max = 15)
    private String name;
    @NotNull
    @Size(min = 2, max = 15)
    private String lastName;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(precision = 9, scale = 2)
    @Min(value = 0)
    private BigDecimal balance = BigDecimal.ZERO;
    @NotNull
    private String encryptedPassword;
    @NotNull
    private boolean active = true;
    @NotNull
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name ="USER_ROLES",
            joinColumns = @JoinColumn(name="USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles = new HashSet<>();
    @NotNull
    @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
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
