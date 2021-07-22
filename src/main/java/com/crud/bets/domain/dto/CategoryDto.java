package com.crud.bets.domain.dto;

import java.util.Set;

public class CategoryDto {
    private long categoryId;
    private String name;
    private Set<EventDto> events;
}
