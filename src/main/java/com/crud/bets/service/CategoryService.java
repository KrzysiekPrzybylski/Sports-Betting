package com.crud.bets.service;

import com.crud.bets.domain.Category;
import com.crud.bets.domain.Event;
import com.crud.bets.exception.CategoryNotFoundException;
import com.crud.bets.exception.EventNotFoundException;
import com.crud.bets.repository.CategoryRepository;
import com.crud.bets.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    public CategoryService(CategoryRepository categoryRepository, EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategory(long categoryId) throws CategoryNotFoundException{
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    public Category changeName(long categoryId, String name) throws CategoryNotFoundException{
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        category.setName(name);
        return categoryRepository.save(category);
    }
    public void deleteCategory(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
    public Category addEvent(long categoryId, long eventId) throws CategoryNotFoundException, EventNotFoundException{
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        event.setCategory(category);
        category.getEvents().add(event);
        return categoryRepository.save(category);
    }
}
