package com.crud.bets.mapper;

import com.crud.bets.domain.Category;
import com.crud.bets.domain.dto.CategoryDto;
import com.crud.bets.domain.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class CategoryMapper {

    private final EventMapper eventMapper;

    @Autowired
    public CategoryMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }
    public CategoryDto mapToCategoryDto(Category category) {
        Set<EventDto> events = category.getEvents().stream()
                .map(eventMapper::mapToEventDto)
                .collect(Collectors.toSet());
        return  new CategoryDto(category.getCategoryId(), category.getName(), events);
    }
    public List<CategoryDto> mapToCategoryDtoList(List<Category> categories) {
        return categories.stream()
                .map(this::mapToCategoryDto)
                .collect(Collectors.toList());
    }
}
