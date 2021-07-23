package com.crud.bets.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class CategoryDto {
    private long categoryId;
    private String name;
    private Set<EventDto> events;


}
