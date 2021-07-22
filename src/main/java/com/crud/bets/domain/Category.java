package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "CATEGORIES")
public class Category {

    @NotNull
    @Id
    @GeneratedValue
    private long categoryId;
    @NotNull
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Event> events = new HashSet<>();
}
