package com.crud.bets.controller;

import com.crud.bets.domain.Category;
import com.crud.bets.domain.dto.CategoryDto;
import com.crud.bets.domain.dto.NameDto;
import com.crud.bets.exception.CategoryNotFoundException;
import com.crud.bets.exception.EventNotFoundException;
import com.crud.bets.mapper.CategoryMapper;
import com.crud.bets.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryMapper.mapToCategoryDtoList(categoryService.getCategories());
    }
    @GetMapping("/{categoryId}")
    public  CategoryDto getCategory(@PathVariable long categoryId) throws CategoryNotFoundException {
        return categoryMapper.mapToCategoryDto(categoryService.getCategory(categoryId));
    }
    @PostMapping
    public CategoryDto addCategory(@RequestBody NameDto nameDto) {
        Category category = new Category();
        category.setName(nameDto.getName());
        return categoryMapper.mapToCategoryDto(categoryService.addCategory(category));
    }
    @PatchMapping("/{categoryId}")
    public CategoryDto editCategory(@PathVariable long categoryId, @RequestBody NameDto nameDto) throws CategoryNotFoundException {
        return categoryMapper.mapToCategoryDto(categoryService.changeName(categoryId, nameDto.getName()));
    }
    @DeleteMapping("/{categoryId")
    public void deleteCategory(@PathVariable long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
    @PatchMapping("/{categoryId}/events/{eventId}")
    public CategoryDto addEventToCategory(@PathVariable long categoryId, @PathVariable long eventId) throws CategoryNotFoundException, EventNotFoundException {
        return categoryMapper.mapToCategoryDto(categoryService.addEvent(categoryId, eventId));
    }
}
