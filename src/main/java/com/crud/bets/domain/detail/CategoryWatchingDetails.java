package com.crud.bets.domain.detail;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
public class CategoryWatchingDetails {

    @NotNull
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private LocalDateTime dateTime;
    @NotNull
    private long categoryId;

    public  CategoryWatchingDetails() {

    }
    public CategoryWatchingDetails(@NotNull long categoryId) {
        this.dateTime = LocalDateTime.now();
        this.categoryId = categoryId;
    }
}
